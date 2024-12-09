# 📞 Phone Contact Manager
# 📋 Overview
The Phone Contact Manager is a Java-based application that uses a Hash Table to store and manage contacts. It enables users to efficiently perform operations like adding, searching, updating, deleting, and displaying phonebook entries. The application employs linear probing for collision resolution and ensures uniqueness of phone numbers.

# ✨ Features
1. ✅ Add Contact: Store a new contact with a name and phone number.
2. 🔍 Search Contact: Find a contact by name and display their phone number.
3. 🗑️ Delete Contact: Remove a contact by name and rehash subsequent entries.
4. 🛠️ Update Contact: Modify the phone number of an existing contact.
5. 📜 Display Contacts: List all stored contacts in the hash table.
6. 🚪 Exit: Exit the program.
# 🔧 How It Works

# Data Structure
The application uses a Hash Table with the following features:

1. Hash Function: Maps names to indices using bitwise and arithmetic operations to minimize collisions.
2. Collision Handling: Resolves conflicts using linear probing (open addressing).
3. Rehashing: Automatically reinserts affected entries after a deletion to maintain data integrity.
   
# Functional Details
1. Add Contact

Calculates the hash index for the contact name.
Verifies the phone number is unique across the table.
Handles collisions via linear probing.

2. Delete Contact

Locates the contact by name.
Removes the entry and rehashes subsequent entries for consistency.

3. Search Contact

Probes the table for a name and retrieves the associated phone number.
4. Update Contact

Locates the contact and updates its phone number.
5. Display Contacts

Iterates through the table and prints all valid entries.

# 🚀 Benefits of the Hash Table
1. Fast Lookups: Nearly O(1) time complexity for search, insert, and delete operations.
2. Collision Handling: Linear probing ensures the table remains consistent even with collisions.
3. Dynamic Organization: Adjusts seamlessly when entries are added or removed.

# ⚠️ Limitations
1. The hash table has a fixed bucket size; resizing is not yet implemented.
2. Performance may degrade when the hash table is nearly full.
3. Collisions increase lookup time due to linear probing.

# 🏁 Conclusion
The Phone Contact Manager is a simple yet powerful implementation of a hash table to manage contacts.
It combines efficiency, ease of use, and scalability. This application is an excellent tool to learn about hashing, collision resolution, and implementing dynamic data structures in Java.

