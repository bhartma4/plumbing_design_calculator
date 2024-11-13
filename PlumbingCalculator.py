def dfu_calculation():
    # Define items and their values
    items = {
        "Automatic clothes washers, commercial": 3,
        "Automatic clothes washers, residential": 2,
        "Bathroom group (1.6 gpf water closet)": 5,
        "Bathroom group (water closet flushing > 1.6 gpf)": 6,
        "Bathtub (with/without shower or whirlpool)": 2,
        "Bidet": 1,
        "Combination sink and tray": 2,
        "Dental lavatory": 1,
        "Dental unit or cuspidor": 1,
        "Dishwashing machine, domestic": 2,
        "Drinking fountain": 0.5,
        "Emergency floor drain": 0,
        "Floor drains": 2,
        "Floor sinks": "Note h",
        "Kitchen sink, domestic": 2,
        "Kitchen sink, domestic (with food waste disposer or dishwasher)": 2,
        "Laundry tray (1 or 2 compartments)": 2,
        "Lavatory": 1,
        "Shower (flow rate 5.7 gpm or less)": 2,
        "Shower (flow rate > 5.7 gpm to 12.3 gpm)": 3,
        "Shower (flow rate > 12.3 gpm to 25.8 gpm)": 5,
        "Shower (flow rate > 25.8 gpm to 55.6 gpm)": 6,
        "Service sink": 2,
        "Sink": 2,
        "Urinal": 4,
        "Urinal, 1 gallon per flush or less": 2,
        "Urinal, nonwater supplied": 0.5,
        "Wash sink (circular or multiple), each set of faucets": 2,
        "Water closet, flushometer tank, public or private": 4,
        "Water closet, private (1.6 gpf)": 3,
        "Water closet, private (flushing > 1.6 gpf)": 4,
        "Water closet, public (1.6 gpf)": 4
    }
    
    # Initialize total dfu
    total_quantity = 0
    
    while True:
        # Display items to choose from
        print("\nPlease choose an item or type 'total' to finish:")
        for index, (item, DFU) in enumerate(items.items(), start=1):
            print(f"{index}. {item} - {DFU} dfu")
        
        # Get user selection
        choice = input("Enter the number of the item you want to choose or 'total' to finish: ").strip().lower()
        
        if choice == 'total':
            break
        
        # Convert choice to an item if it's a valid number
        try:
            choice = int(choice)
            item_name = list(items.keys())[choice - 1]
        except (ValueError, IndexError):
            print("Invalid choice. Please select a valid item number or type 'total' to finish.")
            continue
        
        # Get quantity
        try:
            quantity = int(input(f"How many {item_name}s would you like? "))
            if quantity < 1:
                raise ValueError
        except ValueError:
            print("Please enter a valid quantity.")
            continue

        # Calculate and add to total dfu
        item_quantity = items[item_name] * quantity
        total_quantity += item_quantity
        print(f"Added {quantity} {item_name}(s) - {item_quantity} to your total.")
    
    # Display the final total dfu
    print(f"\nYour total DFU is: {total_quantity}")

# Run the function
dfu_calculation()