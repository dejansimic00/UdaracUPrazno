import cv2
import requests
import face_recognition
import threading
import time
from pyzbar.pyzbar import decode

def scan_qr_code():
    cap = cv2.VideoCapture(0)
    while True:
        ret, frame = cap.read()
        decoded_objects = decode(frame)
        for obj in decoded_objects:
            num = obj.data.decode()
            print(obj.data.decode())
            url = 'http://192.168.20.205/evidencijaRadnogVremena/pristupiProstoriji.php?id_osoba={}&id_prostorija=1'.format(num)
            response = requests.get(url)

            
            if response.status_code == 200:
            # Print the response content (the data received from the server)
                print(response.text)
                nazivFunkcije(response)
                #######################poziv funkcije za poredjenje slike i face
                # cap.release()
                # cv2.destroyAllWindows()
                return
            else:
            # Print an error message if the request was not successful
                print('Error:', response.status_code)
                

            
        cv2.imshow('QR Code Scanner', frame)
        if cv2.waitKey(1) & 0xFF == ord('q'):
            break
    cap.release()
    cv2.destroyAllWindows()
    
def nazivFunkcije(slika):
    while True:
        # Grab a single frame of video
        ret, frame = video_capture.read()
        # Scan QR code and display the header
        ret_val = scan_qr_code(frame)
        # Only process every other frame of video to save time
        if process_this_frame:
            # Resize frame of video to 1/4 size for faster face recognition processing
            small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)

            # Convert the image from BGR color (which OpenCV uses) to RGB color (which face_recognition uses)
            rgb_small_frame = np.ascontiguousarray(small_frame[:, :, ::-1])
            
            # Find all the faces and face encodings in the current frame of video
            face_locations = face_recognition.face_locations(rgb_small_frame)
            face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)

            face_names = []
            for face_encoding in face_encodings:
                # See if the face is a match for the known face(s)
                matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
                name = "Unknown"

                # # If a match was found in known_face_encodings, just use the first one.
                # if True in matches:
                #     first_match_index = matches.index(True)
                #     name = known_face_names[first_match_index]

                # Or instead, use the known face with the smallest distance to the new face
                face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
                best_match_index = np.argmin(face_distances)
                if matches[best_match_index]:
                    name = known_face_names[best_match_index]

                face_names.append(name)

        process_this_frame = not process_this_frame
    
# while True:
#     # Grab a single frame of video
#     ret, frame = video_capture.read()
#     # Scan QR code and display the header
#     ret_val = scan_qr_code(frame)
#     # Only process every other frame of video to save time
#     if process_this_frame:
#         # Resize frame of video to 1/4 size for faster face recognition processing
#         small_frame = cv2.resize(frame, (0, 0), fx=0.25, fy=0.25)

#         # Convert the image from BGR color (which OpenCV uses) to RGB color (which face_recognition uses)
#         rgb_small_frame = np.ascontiguousarray(small_frame[:, :, ::-1])
        
#         # Find all the faces and face encodings in the current frame of video
#         face_locations = face_recognition.face_locations(rgb_small_frame)
#         face_encodings = face_recognition.face_encodings(rgb_small_frame, face_locations)

#         face_names = []
#         for face_encoding in face_encodings:
#             # See if the face is a match for the known face(s)
#             matches = face_recognition.compare_faces(known_face_encodings, face_encoding)
#             name = "Unknown"

#             # # If a match was found in known_face_encodings, just use the first one.
#             # if True in matches:
#             #     first_match_index = matches.index(True)
#             #     name = known_face_names[first_match_index]

#             # Or instead, use the known face with the smallest distance to the new face
#             face_distances = face_recognition.face_distance(known_face_encodings, face_encoding)
#             best_match_index = np.argmin(face_distances)
#             if matches[best_match_index]:
#                 name = known_face_names[best_match_index]

#             face_names.append(name)

#     process_this_frame = not process_this_frame




if __name__ == "__main__":
    scan_qr_code()
