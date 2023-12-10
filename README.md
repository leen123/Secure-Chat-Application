# Secure Chat Application

This project is part of the Computer Engineering program at Damascus University. It is a secure chat application similar to WhatsApp, designed to ensure data privacy and encryption. The project is built using Java and focuses on implementing security mechanisms such as encryption, identity verification, and key management.

## Features

### Client Application:

- Account Creation: Users can create an account securely.
- Login and Logout: Users can log in and out of their accounts.
- Account Recovery: A mechanism is provided for account recovery in case of password loss.
- Contact List: Users can view all contacts and saved phone numbers.
- Select Contact: Users can choose a contact to chat with.
- Message History: Users can view all messages between two clients.
- View Sent Messages: Users can see messages sent by the other client.
- Real-time Messaging: Messages are received and displayed in real-time.
- Key Pair Generation and CSR: Key pairs and Certificate Signing Requests (CSRs) can be generated securely.

### Server Application:

- Request Handling: The server processes incoming requests from clients.
- Database Management: The server manages the user database.
- Key Pair Generation and CSR: The server generates key pairs and CSRs.
- Connection Management: The server handles communication between clients.

### Certificate Authority (CA) Application:

- CSR Generation: The CA can generate CSRs.
- Certificate Validation: The CA validates and manages certificates.

## Security Measures

- Data Encryption: Data is encrypted using both symmetric (AES in CBC and GCM modes) and hybrid encryption (PGP) to ensure confidentiality.
- Digital Signature: Data is digitally signed to verify authenticity.
- CSR Usage: Certificate Signing Requests (CSRs) are used for identity verification.
- Authentication and Authorization: Permission management is enforced.
- End-to-End Encryption: Messages are encrypted between clients.
- Password Encryption: Passwords are securely hashed and stored in the database.

## Usage

To run the Secure Chat Application, ensure you have Java installed on your machine. Follow these steps:

1. Clone this repository to your local machine.
2. Open the project in your preferred Java IDE.
3. Compile and run the main application files for the Client, Server, and CA.

## Documentation

For detailed documentation on security implementations, please refer to the [Report Information Security](/Report%20Information%20Security.pdf).

## Contributors

- Leen Alashkar
- Marymar Radwan
- Ahymad Mriwed

## License

This project is open-source and available under the [MIT License](/LICENSE).
