package utp.misiontic2022.c2.g39.reto4.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utp.misiontic2022.c2.g39.reto4.model.vo.Requerimiento_1;
import utp.misiontic2022.c2.g39.reto4.util.JDBCUtilities;

public class RequerimientoDao_1 {
    public ArrayList<Requerimiento_1> requerimiento() throws SQLException {
        ArrayList<Requerimiento_1> respuesta = new ArrayList<Requerimiento_1> ();
        Connection conexion = JDBCUtilities.getConnection();

        try {
            String consulta = "SELECT c.fecha, c.ID_Compra, c.Proveedor, c.Pagado " + 
                              "FROM Compra c ORDER BY c.ID_Compra DESC LIMIT 15";

            PreparedStatement statement = conexion.prepareStatement(consulta);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Requerimiento_1 requerimiento_1 = new Requerimiento_1();
                requerimiento_1.setFechaCompra(resultSet.getString("Fecha"));
                requerimiento_1.setCodCompra(resultSet.getInt("ID_Compra"));
                requerimiento_1.setNombreProveedor(resultSet.getString("Proveedor"));
                requerimiento_1.setPagado(resultSet.getString("Pagado"));

                respuesta.add(requerimiento_1);
            }

            resultSet.close();
            statement.close();
        } catch(SQLException e) {
            System.err.println("Error consulta SQL Requerimiento_1 -> " + e);
        } finally {
            if(conexion != null) {
                conexion.close();
            }
        }
        return respuesta;
    }
}
