package Wworksop8.chasacademy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MAin {
    static void main() {


        ///Övning 1A – Partitionering och Enkel Gruppering

        List<RestaurantOrder> orders = RestaurantTestData.getOrders();

        //Dela upp alla beställningar i två grupper: Betalda och Obetalda.

        ///Övning 1A – Partitionering
        IO.println("Övning 1A – Partitionering");
        Map<Boolean, List<RestaurantOrder>> ordersByPaymentStatus = orders.stream()
                .collect(Collectors.partitioningBy(RestaurantOrder::isPaid));

        IO.println("Betalda beställningar:" + ordersByPaymentStatus.get(true).size());
        IO.println("Obetalda beställningar:" + ordersByPaymentStatus.get(false).size());

        //Betalda beställningar:3
        //Obetalda beställningar:2


        ///Grouping per OrderType-enum

        Map<RestaurantOrder.OrderType, List<RestaurantOrder>> ordersByType = orders.stream()
                .collect(Collectors.groupingBy(RestaurantOrder::getOrderType));


        ordersByType.forEach((orderType, orderList) -> {
            IO.println(orderType + " beställningar: " + orderList.size());
            orderList.forEach(o -> System.out.println(" " + o.getOrderId() + " - " + o.getCustomerName()));

        });
        //DINE_IN beställningar: 2
        //TAKEAWAY beställningar: 2
        //DELIVERY beställningar: 1

        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");

        ///Övning 1B – Downstream Collectors (Nyckeltal & Statistik)

        IO.println("Övning 1B – Downstream Collectors (Nyckeltal & Statistik)");
        IO.println("Övning 1B – Counting");

        Map<RestaurantOrder.OrderType, Long> orderCountByType  = orders.stream()
                .collect(Collectors.groupingBy(RestaurantOrder::getOrderType, Collectors.counting()));

        orderCountByType.forEach((orderType, orderCount) -> {
            IO.println(orderType + " beställningar: " + orderCount);

        });


        Map<RestaurantOrder.OrderType, Double> averageByType =
                orders.stream()
                        .collect(Collectors.groupingBy(
                                RestaurantOrder::getOrderType,
                                Collectors.averagingDouble(
                                        RestaurantOrder::getTotalPrice
                                )
                        ));
//DINE_IN beställningar: 2
//TAKEAWAY beställningar: 2
//DELIVERY beställningar: 1





        ///Övning 1B – AveragingDouble
        IO.println("Övning 1B – AveragingDouble");
        averageByType.forEach((orderType, averagePrice) -> {

            IO.println(orderType + " genomsnittligt orderpris: " + averagePrice);

            //DINE_IN: 357.50 kr
            //TAKEAWAY: 170.00 kr
            //DELIVERY: 285.00 kr
        });

        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");


        ///Övning 1C – Textformatering & Intäktsberäkning

        IO.println("Övning 1C – Textformatering & Intäktsberäkning");

        //1. Formatera kundlista
        String customerList = orders.stream()
                .map(RestaurantOrder::getCustomerName)
                .distinct()
                .collect(Collectors.joining(", ", "Kundlista: ", "."));

        IO.println(customerList);
        //Kundlista: Anna, Björn, Cecilia, David, Erik.


        //2. Beräkna totala intäkter

        double totalRevenue = orders.stream()
                .filter(RestaurantOrder::isPaid)
                .mapToDouble(RestaurantOrder::getTotalPrice)
                .sum();

        IO.println("Totala intäkter: " + totalRevenue + " kr");
        //Totala intäkter: 1340.0 kr



        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");


        //Övning 2A – flatMap()

        IO.println("Övning 2A – flatMap()");

        //Hämta alla sålda matartiklar.

        List<OrderItem> soldFoodItems = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.toList());

        IO.println("Sålda matartiklar: " + soldFoodItems);


        //Övning 2A - Unika drycker: category == "Dryck"

        List<OrderItem> soldDrinks = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .filter(item -> item.getCategory().equals("Dryck"))
                .distinct()
                .collect(Collectors.toList());


        IO.println("Sålda drycker: " + soldDrinks);


        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");
        IO.println("-------------------------------------------");


        //Övning 2B – Populäraste artikeln

        //Hitta namnet på den populäraste maträtten/drycken

        String mostPopularItem = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(OrderItem::getName, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("Ingen artikel hittades");


        IO.println("Populäraste artikeln: " + mostPopularItem);



        //Övning 2C – Refaktorering från imperativ källkod till Streams

        // GAMMAL IMPERATIV KOD: Hitta alla matartiklar (kategori "Mat") som sålts i betalda DINE_IN-order

        List<String> foodItemsInDineIn = new ArrayList<>();
        for (RestaurantOrder order : RestaurantTestData.getOrders()) {
            if (order.isPaid() &&
                    order.getOrderType() == RestaurantOrder.OrderType.DINE_IN) {
                for (OrderItem item : order.getItems()) {
                    if ("Mat".equals(item.getCategory())) {
                        if (!foodItemsInDineIn.contains(item.getName())) {
                            foodItemsInDineIn.add(item.getName());
                        }
                    }
                }
            }
        }

        List<OrderItem> soldFoodItemsInDineInOrders = orders.stream()
                .filter(RestaurantOrder::isPaid)
                .filter(order -> order.getOrderType() == RestaurantOrder.OrderType.DINE_IN)
                .flatMap(order -> order.getItems().stream())
                .filter(item -> item.getCategory().equals("Mat"))
                .collect(Collectors.toList());

        soldFoodItemsInDineInOrders.forEach(
                item ->
                        IO.println("Såld matartikel i betald DINE_IN-order: " + item.getName() + " - " + item.getPrice() + " kr"));
}
}

