package carsharing.command.context;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.customer.ChooseCompanyCarBackCommand;
import carsharing.command.customer.RentCompanyCarCommand;
import carsharing.dao.CarDao;
import carsharing.entity.Car;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ChooseCompanyCarMenu implements Context {
    private static final CarDao carDao = CarDao.get();

    private final Integer companyId;
    private final Integer customerId;
    private final Map<Integer, String> companyCars;

    public ChooseCompanyCarMenu(Integer companyId, Integer customerId) {
        this.companyId = companyId;
        this.customerId = customerId;

        List<Car> companyList = carDao.getCarsForCompany(companyId);
        AtomicInteger idx = new AtomicInteger(1);
        companyCars = companyList.stream()
                .collect(Collectors.toMap(car -> idx.getAndIncrement(), Car::getName));
    }

    @Override
    public void printPrompt() {
        System.out.println("Choose a car:");
        companyCars.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.println(entry.getKey() + ". " + entry.getValue()));
        System.out.println("0. Back");
    }

    @Override
    public Command getCommand(Integer choice) {
        if (choice == 0) {
            return new ChooseCompanyCarBackCommand(companyId, customerId);
        } else {
            return new RentCompanyCarCommand(companyCars.get(choice), companyId, customerId);
        }
    }
}
