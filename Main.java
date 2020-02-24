import java.util.ArrayList;
import java.util.Collections;

class Main {
  public static void main(String[] args) {

 //creating the database   
    FlightDatabase K = new FlightDatabase();
 //   System.out.println(K.flights.get(0).getDetails());

//checking if there exists a flight between two cities
   // K.checkIfFlightExists("Leeds", "Cracow");
   // K.checkIfFlightExists("Leeds", "Tokyo");

//flights from a given city are assinged to ArrayList
ArrayList<Flight>  fromCity =  K.getFlightsFromCity("Leeds");

    K.getFlightsFromCity("Kopenhagen");
System.out.println("----------------------");

//all flights to a given city stored in a ArrayList
  ArrayList<Flight> toCity =  K.getFlightsToCity("Katowice");
    K.getFlightsToCity("Kopenhagen");
System.out.println("----------------------");

//printing flights lists created above 
    K.displayFlights(fromCity);
    K.displayFlights(toCity);
System.out.println("----------------------");

//combo approach
  K.displayFlightsFromCity("Katowice");
  K.displayFlightsToCity("Katowice");
System.out.println("----------------------");
//list of all cities
System.out.println(K.getCities());
    //Flight powrot = new Flight("UK","Krakow");
    //System.out.println(powrot.getDetails());

Flight cheapestFlight = K.getCheapestFlight();
System.out.println("cheapestFlight: " + cheapestFlight.getDetails());

System.out.println( K.getCheapestFlightFromCity("Leeds").getDetails());

  }
}

class Flight {
 String departure;
 String arrival;
 int price;

  public Flight(String departure, String arrival, int price) {
  this.departure = departure;
  this.arrival = arrival;
  this.price = price;
  }

  public String getDetails(){
    return ("Flight from "+this.departure+" to "+this.arrival +" costs "+ this.price);
  }

}

class FlightDatabase {
ArrayList<Flight> flights = new ArrayList<Flight>(); 

public FlightDatabase() {
  this.flights.add(new Flight("Leeds","New York",1800));
  this.flights.add(new Flight("Leeds","Cracow",79));
  this.flights.add(new Flight("Leeds","Warsaw",95));
  this.flights.add(new Flight("Katowice","Manchester",74));
  this.flights.add(new Flight("Warsaw","Manchester",60));
  this.flights.add(new Flight("Katowice","London",103));
  this.flights.add(new Flight("Manchester","Katowice",74));
  this.flights.add(new Flight("Tokyo","Katowice",700));
  this.flights.add(new Flight("London","Tokyo",690));
  this.flights.add(new Flight("Tokyo","London", 1400));
  this.flights.add(new Flight("Manchester","Cracow",45));
  }

public Flight getCheapestFlightFromCity( String city){
  Flight cheapestFlight = null;
  ArrayList<Flight> fromCity = getFlightsFromCity(city);
  for (Flight flight : fromCity){
    if (cheapestFlight == null || flight.price < cheapestFlight.price){
cheapestFlight=flight;
    }
  }

  return cheapestFlight;
}

public Flight getFlights(String start, String end){
  fromCity = this.getFlightsFromCity(start);
  toCity   = this.getFlightsToCity(end);
  for (Flight fFrom : fromCity){
    for (Flight fTo : ToCity ){
      if (fFrom.arrival == fTo.departure){
        return 
      }
    }
  }
}

public Flight getCheapestFlight(){
  Flight cheapestFlight = null;
  for (Flight flight : this.flights){
    if (cheapestFlight == null || flight.price < cheapestFlight.price){
cheapestFlight=flight;
    }
  }

  return cheapestFlight;
}
public ArrayList<String> getCities(){
  ArrayList<String> result = new ArrayList<String>();
  for (Flight flight : this.flights){
    if (!result.contains(flight.departure )){
      result.add(flight.departure);
    }
        if (!result.contains(flight.arrival )){
      result.add(flight.arrival);
        }
  }
  Collections.sort(result);
  return result;
}  

public void checkIfFlightExists(String dep, String arr){
  for (Flight flight: this.flights){
    if (flight.arrival.equals(arr) && flight.departure.equals(dep)){
        System.out.println( "Flight from " + dep + " to " + arr + " is in the database.");
        return;
    }
  }
  System.out.println( "Flight from " + dep + " to " + arr + " is NOT in the database.");
  
}

public ArrayList<Flight> getFlightsFromCity(String city){
  ArrayList<Flight> result = new ArrayList<Flight>();
  for (Flight flight : flights){
    if (flight.departure.equals(city)){
      result.add( flight);
    }
  }
  return result;
}

public ArrayList<Flight> getFlightsToCity(String city){

  ArrayList<Flight> result = new ArrayList<Flight>();
  for (Flight flight : flights){
    if (flight.arrival.equals(city)){
      result.add(flight);
    }
  }
  return result;
}

public void displayFlights(ArrayList<Flight> results){
  if (results.isEmpty()){
    System.out.println("Flight not found");
  }
  for (Flight flight : results){
    System.out.println(flight.getDetails());
  }
}

public void displayFlightsFromCity(String city){
  displayFlights(getFlightsFromCity(city));
}

public void displayFlightsToCity(String city){
  displayFlights(getFlightsToCity(city));
}


}