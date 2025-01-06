/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package module;

import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author Nathanalex
 */
public class CentraleModuleUtils 
{
    public static CentraleModule lookupCentrale() throws Exception
    {
        Properties properties = new Properties();
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        Context ctx = new InitialContext(properties);
        return (CentraleModule)ctx.lookup("ejb:/station//CentraleModuleImpl!module.CentraleModule?stateful");
    }
}
