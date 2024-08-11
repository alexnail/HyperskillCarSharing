package carsharing.command.company;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.CompanyMenu;

public class CompanyCommand implements Command {
    private final Integer companyIdx;

    public CompanyCommand(Integer companyIdx) {
        this.companyIdx = companyIdx;
    }

    @Override
    public Context getContext() {
        return CompanyMenu.get(companyIdx);
    }
}
