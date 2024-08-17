package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.ChooseCompanyMenu;

public class ChooseCompanyCarBackCommand implements Command {
    private final Integer companyId;
    private final Integer customerId;

    public ChooseCompanyCarBackCommand(Integer companyId, Integer customerId) {
        this.companyId = companyId;
        this.customerId = customerId;
    }

    @Override
    public Context getContext() {
        return ChooseCompanyMenu.get(customerId);
    }
}
