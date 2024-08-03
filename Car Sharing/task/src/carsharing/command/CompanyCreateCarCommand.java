package carsharing.command;

import carsharing.command.context.CompanyMenu;

public class CompanyCreateCarCommand implements Command {
    private final Integer companyId;

    public CompanyCreateCarCommand(Integer companyId) {
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
