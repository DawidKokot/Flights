import java.util.ArrayList;
import java.util.Collections; //for sorting

class Main {
  public static void main(String[] args) {
    System.out.println(" ");
    // "hello world" flight
    Flight powrot = new Flight("Hello","World", 101010);
    System.out.println(powrot.getDetails());
    System.out.println("----------------------");
    // creating the database using the constructor
    FlightDatabase K = new FlightDatabase();
    //printing first flight in the K database:
    System.out.println(K.flights.get(0).getDetails());
    System.out.println("----------------------");

    // checking if there exists a flight between two cities
    K.checkIfFlightExists("Leeds", "Cracow");
    K.checkIfFlightExists("Leeds", "Tokyo");
    System.out.println("----------------------");
    // flights from a given city are assinged to ArrayList and then lists are printed
    ArrayList<Flight> fromCity = K.getFlightsFromCity("Leeds");
    System.out.println(fromCity);

    ArrayList<Flight> fromCity2 =K.getFlightsFromCity("Kopenhagen");
    System.out.println(fromCity2);

    System.out.println("----------------------");

    // all flights to a given city stored in a ArrayList and then lists are printed
    ArrayList<Flight> toCity = K.getFlightsToCity("Katowice");
    System.out.println(toCity);
    ArrayList<Flight> toCity2 = K.getFlightsToCity("Kopenhagen");
    System.out.println(toCity2);
    System.out.println("----------------------");

    // combo approach. printing all flight from and to Katowice
    K.displayFlightsFromCity("Katowice");
    K.displayFlightsToCity("Katowice");
    System.out.println("----------------------");

    // list of all cities
    System.out.println(K.getCities());

    //cheapest flight
    Flight cheapestFlight = K.getCheapestFlight();
    System.out.println("cheapest flight: " + cheapestFlight.getDetails());
    //cheapest flight from given city
    System.out.println("cheapest flight from Leeds: " + K.getCheapestFlightFromCity("Leeds").getDetails());
    System.out.println("----------------------");

    //flights with 1 stop
    ArrayList<Journey> journeys = K.getFlights("Tokyo", "Manchester");
    System.out.println(journeys);

  }
}


class Flight {
  String departure;
  String arrival;
  int price;

  //constructor
  public Flight(String departure, String arrival, int price) {
    this.departure = departure;
    this.arrival = arrival;
    this.price = price;
  }

  public String getDetails() {
    return ("Flight from " + this.departure + " to " + this.arrival + " costs " + this.price);
  }

}

//journey with one stop
class Journey {
  Flight first;
  Flight second;

  public Journey(Flight first, Flight second){
    this.first = first;
    this.second = second;
  }

  public String toString(){
    return "Flight from " + this.first.departure + " to " + this.second.arrival + " with stop at " + this.first.arrival + " costs " + (this.first.price+this.second.price); 
  }
}
//////////////////////////// DATABASE CLASS ///////////////////////////////////////
class FlightDatabase {
  ArrayList<Flight> flights = new ArrayList<Flight>();

  //constructor - master flight list
  public FlightDatabase() {
    this.flights.add(new Flight("Leeds", "New York", 1800));
    this.flights.add(new Flight("Leeds", "Cracow", 79));
    this.flights.add(new Flight("Leeds", "Warsaw", 95));
    this.flights.add(new Flight("Katowice", "Manchester", 74));
    this.flights.add(new Flight("Warsaw", "Manchester", 60));
    this.flights.add(new Flight("Katowice", "London", 103));
    this.flights.add(new Flight("Manchester", "Katowice", 74));
    this.flights.add(new Flight("Tokyo", "Katowice", 700));
    this.flights.add(new Flight("London", "Tokyo", 690));
    this.flights.add(new Flight("Tokyo", "London", 1400));
    this.flights.add(new Flight("Manchester", "Cracow", 45));
  }

  public Flight getCheapestFlightFromCity(String city) {
    Flight cheapestFlight = null;
    ArrayList<Flight> fromCity = getFlightsFromCity(city);
    for (Flight flight : fromCity) {
      if (cheapestFlight == null || flight.price < cheapestFlight.price) {
        cheapestFlight = flight;
      }
    }
    return cheapestFlight;
  }

  public ArrayList<Journey> getFlights(String start, String end){
    ArrayList<Journey> result  = new ArrayList<Journey>(); 
    ArrayList<Flight> fromCity = this.getFlightsFromCity(start);
    ArrayList<Flight> toCity   = this.getFlightsToCity(end);
    for (Flight fFrom : fromCity){
      for (Flight fTo : toCity ){
        if (fFrom.arrival.equals(fTo.departure)){
          result.add( new Journey(fFrom, fTo));
        }
      }
    }
    return result;
  }

  public Flight getCheapestFlight() {
    Flight cheapestFlight = null;
    for (Flight flight : this.flights) {
      if (cheapestFlight == null || flight.price < cheapestFlight.price) {
        cheapestFlight = flight;
      }
    }

    return cheapestFlight;
  }

  public ArrayList<String> getCities() {
    ArrayList<String> result = new ArrayList<String>();
    for (Flight flight : this.flights) {
      if (!result.contains(flight.departure)) {
        result.add(flight.departure);
      }
      if (!result.contains(flight.arrival)) {
        result.add(flight.arrival);
      }
    }
    Collections.sort(result);
    return result;
  }

  public void checkIfFlightExists(String dep, String arr) {
    for (Flight flight : this.flights) {
      if (flight.arrival.equals(arr) && flight.departure.equals(dep)) {
        System.out.println("Flight from " + dep + " to " + arr + " is in the database.");
        return;
      }
    }
    System.out.println("Flight from " + dep + " to " + arr + " is NOT in the database.");

  }

  public ArrayList<Flight> getFlightsFromCity(String city) {
    ArrayList<Flight> result = new ArrayList<Flight>();
    for (Flight flight : flights) {
      if (flight.departure.equals(city)) {
        result.add(flight);
      }
    }
    return result;
  }

  public ArrayList<Flight> getFlightsToCity(String city) {

    ArrayList<Flight> result = new ArrayList<Flight>();
    for (Flight flight : flights) {
      if (flight.arrival.equals(city)) {
        result.add(flight);
      }
    }
    return result;
  }

  public void displayFlights(ArrayList<Flight> results) {
    if (results.isEmpty()) {
      System.out.println("Flight not found");
    }
    for (Flight flight : results) {
      System.out.println(flight.getDetails());
    }
  }

  public void displayFlightsFromCity(String city) {
    displayFlights(getFlightsFromCity(city));
  }

  public void displayFlightsToCity(String city) {
    displayFlights(getFlightsToCity(city));
  }

}

