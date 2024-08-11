package carsharing.command.context;

import carsharing.command.*;
import carsharing.dao.CompanyDao;
import carsharing.entity.Company;

public class CompanyMenu implements Context {
    private final CompanyDao dao = CompanyDao.get();
    private final Integer companyIdx;

    public CompanyMenu(Integer companyIdx) {
        this.companyIdx = companyIdx;
    }

    public static Context get(Integer companyIdx) {
        return new CompanyMenu(companyIdx);
    }

    @Override
    public void printPrompt() {
        Company company = dao.getById(companyIdx);
        System.out.printf("'%s' company%n", company.getName());
        System.out.println("1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back");
    }

    @Override
    public Command getCommand(Integer choice) {
        return switch(choice) {
            case 1 -> new CompanyCarListCommand(companyIdx);
            case 2 -> new CompanyCreateCarCommand(companyIdx);
            case 0 -> new CompanyBackCommand();
            default -> throw new IllegalStateException("Unexpected choice: " + choice);
        };
    }
}
