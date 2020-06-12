package edge.temperatura.temperatura.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edge.temperatura.temperatura.models.Alerts;
import edge.temperatura.temperatura.models.Message;
import edge.temperatura.temperatura.models.Trucks;
import edge.temperatura.temperatura.repositories.AlertRepository;
import edge.temperatura.temperatura.repositories.TruckRepository;

@Service
public class TrucksServiceImpl {

    @Autowired 
    private TruckRepository truckRepository;

    @Autowired
    private AlertRepository alertRepository;


    public Map<String, Trucks> getMapOfTrucks(){
        
        List<Trucks> listTrucks = truckRepository.findAll();
        Map<String, Trucks> trucks = new HashMap<>();

        for( int i = 0; i < listTrucks.size(); i++){
            trucks.put(listTrucks.get(i).getHostname(), listTrucks.get(i));
        }
               

        return trucks;
    }

    public Trucks createAlert(Message message, Trucks truck){
        
        Alerts newAlert = new Alerts(message.getTimestamp(), message.getTemperature(), message.getHumidity(), message.getTempThreshold());

        truck.addAlert(newAlert.get_id());
        alertRepository.save(newAlert);
        truckRepository.save(truck);

        return truck;
    }

    public Trucks createTruck(Message newMessage){

        Trucks truck = new Trucks(newMessage.getHostname(), newMessage.getEnv());
        truckRepository.save(truck);

        return truck;
    }
    
}