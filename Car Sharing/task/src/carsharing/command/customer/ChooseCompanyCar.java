package carsharing.command.customer;

import carsharing.command.Command;
import carsharing.command.Context;
import carsharing.command.context.ChooseCompanyCarMenu;

public class ChooseCompanyCar implements Command {
    private final Integer companyId;
    private final Integer customerId;

    public ChooseCompanyCar(Integer companyId, Integer customerId) {
        this.companyId = companyId;
        this.customerId = customerId;
    }

    @Override
    public Context getContext() {
        return new ChooseCompanyCarMenu(companyId, customerId);
    }
}
