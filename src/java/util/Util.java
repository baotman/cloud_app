/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author MedEM
 */
public class Util {

    public static String getParamsFromRequest(HttpServletRequest request) {
        String params = "";
        String values = "";

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if(paramName.equals("tableName")) continue;
            String[] paramValues = request.getParameterValues(paramName);
            params += paramName;
            values += "'" + paramValues[0] + "'";
//            for (int i = 0; i < paramValues.length; i++) {
//                String paramValue = paramValues[i];
//                pra
//            }
            if(parameterNames.hasMoreElements()){
                params += ",";
                values += ",";
            }

        }
        
        return "(" + params +") values (" + values + ")";

    }

    public static String getUpdateFromReqest(HttpServletRequest request) {
        String params = "";

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.equals("tableName" ) || paramName.equals("rowId") || paramName.equals("id")) {
                continue;
            }
            String[] paramValues = request.getParameterValues(paramName);
            params += paramName + "=";
            params += "'" + paramValues[0] + "' ";
//            for (int i = 0; i < paramValues.length; i++) {
//                String paramValue = paramValues[i];
//                pra
//            }
            if (parameterNames.hasMoreElements()) {
                params += ", ";
            }

        }

        return params;
    }

}
