package org.example.DDT;

import org.example.utils.utilsExcel;
import org.testng.annotations.Test;

public class LoginWithDDT {

    @Test(dataProvider = "getData", dataProviderClass = utilsExcel.class)
    public void testLoginData(String username, String password){
        System.out.println("username"  + " " + username);
        System.out.println("password" + " " + password);
    }
}
