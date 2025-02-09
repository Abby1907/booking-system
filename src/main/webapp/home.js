const services = {
            "AC & Appliances": ["AC Repair", "TV Repair", "Fridge Repair", "Washing Machine Repair"],
            "House Cleaning": ["1BHK", "2BHK", "3BHK", "Bathroom Cleaning"],
            "Vehicle Repair Service": ["2-Wheeler Repair", "3-Wheeler Repair"],
            "Product Pickup & Drop": ["Furniture", "Groceries", "Vehicles"]
        };

        let selectedServices = [];

        function selectService(service) {
            document.getElementById('form-container').style.display = 'block';
            document.getElementById('bill-container').style.display = 'block';
            document.getElementById('service-title').textContent = service;

            const subServiceDropdown = document.getElementById('sub-service');
            subServiceDropdown.innerHTML = '';

            services[service].forEach(subService => {
                const option = document.createElement('option');
                option.value = subService;
                option.textContent = subService;
                subServiceDropdown.appendChild(option);
            });
        }

        function addService() {
            const service = document.getElementById('service-title').textContent;
            const subService = document.getElementById('sub-service').value;
            const dateSlot = document.getElementById('date-slot').value;
            const address = document.getElementById('address').value;
            const vendor = document.getElementById('vendor').value;
            const amount = 100; // Fixed amount for simplicity
            
            selectedServices.push({ service, subService, dateSlot, address, vendor, amount });
            updateBill();
        }

        function updateBill() {
            let totalAmount = 0;
            const billDetails = document.getElementById('bill-details');
            billDetails.innerHTML = '';

            selectedServices.forEach((service, index) => {
                totalAmount += service.amount;
                billDetails.innerHTML += `
                    <p>
                        <strong>${index + 1}. ${service.service} - ${service.subService}</strong><br>
                        Date: ${service.dateSlot}<br>
                        Address: ${service.address}<br>
                        Vendor: ${service.vendor}<br>
                        Amount: $${service.amount}
                    </p>
                `;
            });

            document.getElementById('total-amount').textContent = totalAmount;
        }

		function confirmBooking() {
		    if (selectedServices.length === 0) {
		        alert("Please add at least one service before confirming.");
		        return;
		    }

			fetch("http://localhost:8080/BookingSystem/Booking", {
			    method: "POST",
			    headers: { "Content-Type": "application/json" },
				body: JSON.stringify({ bookingData: selectedServices })
			})
		    .then(response => response.json())
		    .then(data => {
		        if (data.status === "success") {
		            document.getElementById('bill-container').style.display = 'none';
		            document.getElementById('success-message').style.display = 'block';
		            document.getElementById('booking-details').textContent = "Total Amount: $" + selectedServices.reduce((sum, service) => sum + service.amount, 0);
		        } else {
		            alert(data.message);
		        }
		    })
		    .catch(error => console.error("Error:", error));
		}
		function logout() {
			fetch("http://localhost:8080/BookingSystem/logout", {
				method: "POST",
				credentials: "include"
			})
			.then(response => {
				if (response.ok) {
				            window.location.href = "login.jsp"; // Redirect to login page after logout
				        }
			})
			.catch(error => {
				alert("Logout failed!" + error);
			})
		}

