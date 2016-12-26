/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package by.phone.station.command.factory;

import by.phone.station.command.ActionCommand;
import by.phone.station.command.admin.UnBlockUserCommand;
import by.phone.station.command.admin.AddAbonentsCommand;
import by.phone.station.command.admin.AddAdminCommand;
import by.phone.station.command.admin.AddServiceCommand;
import by.phone.station.command.admin.BlockUserCommand;
import by.phone.station.command.admin.DeleteAdminsCommand;
import by.phone.station.command.admin.DeleteServiceCommand;
import by.phone.station.command.admin.DeleteUserCommand;
import by.phone.station.command.admin.DetailServiceCommand;
import by.phone.station.command.admin.DetailUserCommand;
import by.phone.station.command.admin.GetAbonentsCommand;
import by.phone.station.command.admin.GetAdminsCommand;
import by.phone.station.command.admin.GetMainCommand;
import by.phone.station.command.admin.GetServicesCommand;
import by.phone.station.command.admin.LoginCommand;
import by.phone.station.command.admin.LogoutCommand;
import by.phone.station.command.admin.UpdateAbonentsCommand;
import by.phone.station.command.admin.UpdateServiceCommand;
import by.phone.station.command.client.AddServiceForCommand;
import by.phone.station.command.client.ChangePasswordCommand;
import by.phone.station.command.client.GetAboutCommand;
import by.phone.station.command.client.GetContactsCommand;
import by.phone.station.command.client.GetHomePageAbonentCommand;
import by.phone.station.command.client.GetServicesClientCommand;
import by.phone.station.command.client.LoginAbonentCommand;
import by.phone.station.command.client.LogoutAbonentCommand;
import by.phone.station.command.client.PaymentServiceCommand;

/**
 *
 * @author user
 */

//перечисление, содержащей связь между командоуй и обрабочиком команды
public enum CommandEnum {
    LOGIN {
    {
        this.command = new LoginCommand();
    }
    },
    LOGOUT {
    { 
        this.command = new LogoutCommand();
    }
    },
    GETABONENTS {
    { 
        this.command = new GetAbonentsCommand();
    }
    },
    ADDABONENTS {
    { 
        this.command = new AddAbonentsCommand();
    }
    },
    UPDATEUSER {
    { 
        this.command = new UpdateAbonentsCommand();
    }
    },
    DETAILUSER {
    { 
        this.command = new DetailUserCommand();
    }
    },
    BLOCKUSER {
    { 
        this.command = new BlockUserCommand();
    }
    },
    UNBLOCKUSER {
    { 
        this.command = new UnBlockUserCommand();
    }
    },
    DELETEUSER {
    { 
        this.command = new DeleteUserCommand();
    }
    },
    GETSERVICES {
    { 
        this.command = new GetServicesCommand();
    }
    },
    ADDSERVICE{
    { 
        this.command = new AddServiceCommand();
    }
    },
    DETAILSERVICE{
    { 
        this.command = new DetailServiceCommand();
    }
    },
    UPDATESERVICE{
    { 
        this.command = new UpdateServiceCommand();
    }
    },
    DELETESERVICE {
    { 
        this.command = new DeleteServiceCommand();
    }
    },
    GETMAIN {
    { 
        this.command = new GetMainCommand();
    }
    },
    GETADMINS{
    { 
        this.command = new GetAdminsCommand();
    }
    },
    DELETEADMIN {
    { 
        this.command = new DeleteAdminsCommand();
    }
    },
    ADDADMIN {
    { 
        this.command = new AddAdminCommand();
    }
    },
    LOGINABONENT {
    { 
        this.command = new LoginAbonentCommand();
    }
    },
    LOGOUTABONENT {
    { 
        this.command = new LogoutAbonentCommand();
    }
    },
    GETSERVICESCLIENT{
    { 
        this.command = new GetServicesClientCommand();
    }
    },
    ADDSERVICEFOR{
    { 
        this.command = new AddServiceForCommand();
    }
    },
    PAYMENTSERVICE{
    { 
        this.command = new PaymentServiceCommand();
    } 
    },
    GETABOUT{
    { 
        this.command = new GetAboutCommand();
    }
    },
    GETCONTACTS{
    { 
        this.command = new GetContactsCommand();
    }
    },
    GETHOMEPAGEABONENT{
    { 
        this.command = new GetHomePageAbonentCommand();
    }
    },
    CHANGEPASSWORD{
    { 
        this.command = new ChangePasswordCommand();
    }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
