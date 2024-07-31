#!/usr/bin/bash

userFile="user.txt"
# Function to create a user
createUser() {
    local username=$1
    local password=$2
    local role=$3
    local uid==$(uuidgen)

    # validate role
    if [[ "$role" != "admin" && "$role" != "patient" ]]; then
        echo "Invalid role. Please enter either admin or patient."
        return
    fi
    # Check if user already exists
    if grep -q "^$username$" "$userFile" ; then
        echo "User $username already exists in our database."
    else
        # Create user and set password
        echo "$username:$password:$role:$uid" >> "$userFile"
        echo "User $username with $uid created and added to our database."
    fi
}

# Function to create an initial admin
createAdminZero() {
    local adminZeroUsername="admin"
    local adminPassword="A1234"
    
    
    if grep -q "^$adminZeroUsername" "$userFile"; then
        :
    else
        createUser "$adminZeroUsername" "$adminPassword" "admin"
    fi
}

adminFunctions(){
    echo "1. Create a new user"
    echo "2. Delete a user"
    echo "3. View all users"
    echo "4. Logout"
    read -p "Enter your choice: " choice

    case $choice in
        1)
            read -p "Enter username: " username
            read -sp "Enter password: " password
            echo
            read -p "Enter role: " role
            createUser "$username" "$password" "$role"
            adminFunctions
            ;;
        2)
            read -p "Enter username to delete: " username
            sed -i "/^$username/d" "$userFile"
            echo "User $username deleted."
            adminFunctions
            ;;
        3)
            cat "$userFile"
            adminFunctions
            ;;
        4)
            loginUser
            ;;
        *)
            echo "Invalid choice. Please try again."
            adminFunctions
            ;;
    esac
}

patientFunctions(){
    echo "1. View your profile"
    echo "2. Update your profile"
    echo "3. Compute life expectancy"
    echo "4. Logout"
    read -p "Enter your choice: " choice

    case $choice in
        1)
            echo "Viewing profile"
            ;;
        2)
            echo "Updating your profile"
            ;;
        3)
            echo "Time is catching up with you. Computing life expectancy..."
            ;;
        4)
            loginUser
            ;;
        *)
            echo "Invalid choice. Please try again."
            patientFunction
            ;;
    esac
}

loginUser() {
    local username=$1
    local password=$2

    read -p "Enter your username: " username
    read -sp "Enter your password: " password
    echo

    if grep -q "^$username:$password:" "$userFile"; then
        echo "Welcome $username. You have successfully logged in."
        local role=$(grep "^$username:$password:" "$userFile" | cut -d':' -f3)
        if [ "$role" == "admin" ]; then
            adminFunctions
        else
            patientFunctions
        fi
    else
        echo "Invalid username or password. Please try again."
        loginUser
    fi
}

echo "Welcome to LPMT - Your healtcare clock-in system. Please login to access our services"
createAdminZero
loginUser

