package carsharing.command;

import carsharing.command.context.CompanyMenu;

public class CompanyCarListCommand implements Command {

    private final Integer companyId;

    public CompanyCarListCommand(Integer companyId) {
        this.companyId = companyId;
    }

    @Override
    public boolean execute() {
        return Command.super.execute();
    }

    @Override
    public Context getContext() {
        return CompanyMenu.get(companyId);
    }
}
