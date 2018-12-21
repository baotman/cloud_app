
import dao.AbstractDAO;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MedEM
 */
public class Test {
    public static void main(String[] args){
        
        try {
            AbstractDAO cnint = new AbstractDAO();
            Map<String, String> columns = cnint.getValues("clients", "1");
            
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
