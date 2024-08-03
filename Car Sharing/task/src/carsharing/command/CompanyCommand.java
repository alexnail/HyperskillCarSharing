package carsharing.command;

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
