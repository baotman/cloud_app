package dao;

import bean.Entity;
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import util.Util;

/**
 *
 * @author MedEM
 */
public class AbstractDAO {

    String DB_NAME = "test";
    Connection conn;

    public AbstractDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, "root", "");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public LinkedList<String> getTablesName() {
        try {
            LinkedList<String> tables = new LinkedList<>();
            DatabaseMetaData md = conn.getMetaData();
                ResultSet rs = md.getTables(null, null, "%", null);
                while (rs.next()) {
                    tables.add(rs.getString(3));
                }
                return tables;
            } catch (SQLException ex) {
                Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }

        public LinkedList<Entity> findAll(Class<?> entity) throws IntrospectionException {
        try {
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * from " + entity.getSimpleName());
            LinkedList<Entity> clients = new LinkedList<>();
            while (rs.next()) {

                BeanInfo beanInfo = Introspector.getBeanInfo(Entity.class);
                for (PropertyDescriptor propertyDesc : beanInfo.getPropertyDescriptors()) {
                    String propertyName = propertyDesc.getName();
                    Class<?> type = propertyDesc.getPropertyType();

/// 
                }
            }
            return clients;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Map<String, String> getTableSchema(String table) {
        try {

            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * from " + table);

            Map<String, String> columns = new HashMap<>();
//            
//            DatabaseMetaData databaseMetaData = conn.getMetaData();

//            ResultSet rs  = databaseMetaData.getColumns(null, null, table, null);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.put(metaData.getColumnName(i), metaData.getColumnTypeName(i));
            }
            return columns;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public <T> ArrayList<T> getList(Class<T> klazz, String name) throws ClassNotFoundException, SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Object actuallyT = null;
        ArrayList<T> list = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + name);

        ResultSetMetaData md = rs.getMetaData();
        int colN = md.getColumnCount();
        Class types[] = new Class[colN];
        for (int i = 0; i < colN; i++) {
            types[i] = Object.class;
        }

        ArrayList<Object> data = new ArrayList<>();
        while (rs.next()) {
            for (int i = 1; i <= colN; i++) {
                data.add(rs.getObject(i));
            }
            list.add(klazz.getConstructor(types).newInstance(data.toArray()));
            data.clear();
        }
        list.add(klazz.cast(actuallyT));
        return list;
    }

    public Map<String, String> getValues(String name, String id) {
        try {
            Map<String, String> columns = new HashMap<>();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + name + " WHERE id='" + id + "'");

            ResultSetMetaData md = rs.getMetaData();
            int colN = md.getColumnCount();
            for (int i = 1; i <= colN; i++) {
                System.err.println(i + " : " + md.getColumnName(i));
                columns.put(md.getColumnName(i), "");
            }

//        columns = getTableSchema(name);
            while (rs.next()) {
                for (Map.Entry<String, String> entry : columns.entrySet()) {
                    String key = entry.getKey();
                    String value = rs.getObject(key) != null ? rs.getObject(key).toString() : "";
                    columns.put(key, value);
                }
            }
            return columns;
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Map<String, String> getAllValues(String name) throws ClassNotFoundException, SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException {
        Map<String, String> rows = new HashMap<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + name);

        while (rs.next()) {
            ResultSetMetaData md = rs.getMetaData();
            int colN = md.getColumnCount();
            String contenu = " ";
            System.err.println("" + colN);
            for (int i = 2; i <= colN; i++) {
                Object o = rs.getObject(i);
                if (o != null) {
                    contenu += o.toString();
                    if (i < colN) {
                        contenu += " | ";
                    }
                }
            }
            rows.put(rs.getObject(1).toString(), contenu);
        }
        return rows;
    }

    public int add(String table, HttpServletRequest request) {
        try {
            String sql = "INSERT INTO " + table + " " + Util.getParamsFromRequest(request);
            Statement stmt = conn.createStatement();
            System.err.println(sql);
            return stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int delete(String table, String id) {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeUpdate("DELETE FROM " + table + " WHERE id='" + id + "'");
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    } 

    public int edit(String table, String id, HttpServletRequest request) {
        try {
            Statement stmt = conn.createStatement();
            String query = "UPDATE  " + table + " " + " SET " + Util.getUpdateFromReqest(request) + " WHERE id='" + id + "'";
            System.err.println(query);
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(AbstractDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
