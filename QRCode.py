import time
import cv2
from pyzbar.pyzbar import decode
import serial

def blink():
    # Configure the serial port
    ser = serial.Serial('COM10', 9600)  # Change 'COM1' to the appropriate port and 9600 to the baud rate
    time.sleep(2)
    # Send characters
    ser.write('1'.encode('utf-8'))  # Send the string 'Hello', b prefix indicates bytes literal
    # Close the serial port when done
    ser.close()

def scan_qr_code(frame):
    # Decode QR codes
    decoded_objects = decode(frame)

    # Initialize header text and color
    header_text = ""
    header_color = (255, 255, 255)  # Default: white

    # Check if any QR code is detected
    if decoded_objects:
        # Get the first decoded object
        obj = decoded_objects[0]
        decoded_data = obj.data.decode('utf-8')
        
        # Check if the decoded data is a number
        if decoded_data.isdigit():
            number = int(decoded_data)
            
            # Determine header text and color based on whether the number is even or odd
            if number % 2 == 0:
                header_text = "Prijavljen"
                header_color = (0, 255, 0)  # Green
                blink()
                return 1
            else:
                #header_text = "Nema pristup"
                header_color = (0, 0, 255)  # Red

    # Add colored rectangle for the header
    header_height = int(frame.shape[0] * 0.1)  # 10% of frame height
    cv2.rectangle(frame, (0, 0), (frame.shape[1], header_height), header_color, -1)

    # Add header text to the frame
    text_size = cv2.getTextSize(header_text, cv2.FONT_HERSHEY_SIMPLEX, 1, 2)[0]
    text_x = (frame.shape[1] - text_size[0]) // 2
    text_y = (header_height + text_size[1]) // 2
    cv2.putText(frame, header_text, (text_x, text_y), cv2.FONT_HERSHEY_SIMPLEX, 1, (255, 255, 255), 2)

    # Display the frame with the header
    cv2.imshow('Camera', frame)

# Access the camera
cap = cv2.VideoCapture(0)

while True:
    # Capture frame-by-frame
    ret, frame = cap.read()

    # Scan QR code and display the header
    ret_val = scan_qr_code(frame)

    # Check for 'q' key press to exit
    if cv2.waitKey(1) & 0xFF == ord('q'):
        break
    if ret_val == 1:
        break

# Release the camera and close OpenCV window
cap.release()
cv2.destroyAllWindows()
