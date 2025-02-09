document.addEventListener("DOMContentLoaded", function () {
    fetch("http://localhost:8080/BookingSystem/MyOrders")
        .then(response => response.json())
        .then(data => {
            if (data.status === "error") {
                alert(data.message);
                return;
            }

            const ordersTable = document.querySelector("#orders-table tbody");
            ordersTable.innerHTML = "";

            data.forEach(order => {
                const row = `<tr>
                    <td>${order.service}</td>
                    <td>${order.subService}</td>
                    <td>${order.dateSlot}</td>
                    <td>${order.address}</td>
                    <td>${order.vendor}</td>
                    <td>$${order.amount}</td>
                </tr>`;
                ordersTable.innerHTML += row;
            });
        })
        .catch(error => console.error("Error loading orders:", error));
});
