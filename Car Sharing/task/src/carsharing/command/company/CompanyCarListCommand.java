package carsharing.command.company;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.CompanyMenu;
import carsharing.dao.CarDao;
import carsharing.entity.Car;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CompanyCarListCommand implements Command {

    private final Integer companyId;

    public CompanyCarListCommand(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean execute() {
        List<Car> cars = CarDao.get().getCarsForCompany(companyId);
        if (cars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            AtomicInteger i = new AtomicInteger(1);
            cars.stream().sorted(Comparator.comparing(Car::getId))
                    .forEach(car -> System.out.println(i.getAndIncrement() + ". " + car.getName()));
        }
        return true;
    }

    @Override
    public Context getContext() {
        return CompanyMenu.get(companyId);
    }
}
