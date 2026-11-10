package Wworksop8.chasacademy;


import java.util.ArrayList;
import java.util.List;

public class RestaurantTestData {
        public static List<RestaurantOrder> getOrders() {
            List<RestaurantOrder> orders = new ArrayList<>();

            orders.add(new RestaurantOrder("ORD-101", "Anna", RestaurantOrder.OrderType.DINE_IN, List.of(
                    new OrderItem("Hamburgare", "Mat", 145.0),
                    new OrderItem("Pommes", "Mat", 45.0),
                    new OrderItem("Coca Cola", "Dryck", 35.0)
            ), true));

            orders.add(new RestaurantOrder("ORD-102", "Björn", RestaurantOrder.OrderType.TAKEAWAY, List.of(
                    new OrderItem("Pizza Margherita", "Mat", 125.0),
                    new OrderItem("Fanta", "Dryck", 35.0)
            ), true));

            orders.add(new RestaurantOrder("ORD-103", "Cecilia", RestaurantOrder.OrderType.DELIVERY, List.of(
                    new OrderItem("Sushi 12 bitar", "Mat", 165.0),
                    new OrderItem("Miso Soppa", "Mat", 35.0),
                    new OrderItem("Grönt Te", "Dryck", 30.0),
                    new OrderItem("Glass", "Dessert", 55.0)
            ), false));

            orders.add(new RestaurantOrder("ORD-104", "David", RestaurantOrder.OrderType.DINE_IN, List.of(
                    new OrderItem("Entrecôte", "Mat", 295.0),
                    new OrderItem("Rödvin", "Dryck", 110.0),
                    new OrderItem("Tiramisu", "Dessert", 85.0)
            ), true));

            orders.add(new RestaurantOrder("ORD-105", "Erik", RestaurantOrder.OrderType.TAKEAWAY, List.of(
                    new OrderItem("Hamburgare", "Mat", 145.0),
                    new OrderItem("Coca Cola", "Dryck", 35.0)
            ), false));

            return orders;
        }
}
