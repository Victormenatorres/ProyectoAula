package controlador;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

public class Ctrl_RegistrarVenta {
    private static List<CabeceraVenta> cabecerasVenta = new ArrayList<>();
    private static List<DetalleVenta> detallesVenta = new ArrayList<>();
    private static int ultimoIdCabecera = 0;
    private static int ultimoIdDetalle = 0;
    
    public static int idCabeceraRegistrada;

    /**
     * Actualiza una cabecera de venta existente
     */
    public boolean actualizar(CabeceraVenta cabecera, int idCabeceraVenta) {
        try {
            for(int i = 0; i < cabecerasVenta.size(); i++) {
                if(cabecerasVenta.get(i).getIdCabeceraVenta() == idCabeceraVenta) {
                    cabecera.setIdCabeceraVenta(idCabeceraVenta); // Mantener mismo ID
                    cabecerasVenta.set(i, cabecera);
                    
                    JOptionPane.showMessageDialog(null, 
                        "Venta actualizada correctamente", 
                        "Éxito", 
                        JOptionPane.INFORMATION_MESSAGE);
                    return true;
                }
            }
            JOptionPane.showMessageDialog(null, 
                "No se encontró la venta con ID: " + idCabeceraVenta, 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error al actualizar venta: " + e.getMessage(), 
                "Error crítico", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Guarda una nueva cabecera de venta
     */
    public boolean guardar(CabeceraVenta cabecera) {
        try {
            cabecera.setIdCabeceraVenta(++ultimoIdCabecera);
            if(cabecera.getEstado() == 0) cabecera.setEstado(1);
            
            cabecerasVenta.add(cabecera);
            idCabeceraRegistrada = cabecera.getIdCabeceraVenta();
            
            JOptionPane.showMessageDialog(null, 
                "Venta registrada con ID: " + idCabeceraRegistrada, 
                "Registro exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error al guardar venta: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * Guarda un detalle de venta
     */
    public boolean guardarDetalle(DetalleVenta detalle) {
        try {
            if(idCabeceraRegistrada == 0) {
                JOptionPane.showMessageDialog(null, 
                    "Primero debe registrar una cabecera de venta", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            detalle.setIdDetalleVenta(++ultimoIdDetalle);
            detalle.setIdCabeceraVenta(idCabeceraRegistrada);
            
            // Cálculos automáticos si no están establecidos
            if(detalle.getSubTotal() == 0) {
                detalle.setSubTotal(detalle.getPrecioUnitario() * detalle.getCantidad());
            }
            
            detallesVenta.add(detalle);
            
            JOptionPane.showMessageDialog(null, 
                "Detalle de venta agregado correctamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, 
                "Error al guardar detalle: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Métodos de consulta con mensajes opcionales
    public CabeceraVenta obtenerCabeceraPorId(int idCabeceraVenta) {
        for(CabeceraVenta cv : cabecerasVenta) {
            if(cv.getIdCabeceraVenta() == idCabeceraVenta) {
                return cv;
            }
        }
        JOptionPane.showMessageDialog(null, 
            "No se encontró venta con ID: " + idCabeceraVenta, 
            "Consulta", 
            JOptionPane.WARNING_MESSAGE);
        return null;
    }
    
    public List<DetalleVenta> obtenerDetallesVenta(int idCabeceraVenta) {
        List<DetalleVenta> detalles = new ArrayList<>();
        for(DetalleVenta dv : detallesVenta) {
            if(dv.getIdCabeceraVenta() == idCabeceraVenta) {
                detalles.add(dv);
            }
        }
        
        if(detalles.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "No se encontraron detalles para la venta " + idCabeceraVenta, 
                "Consulta", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        return detalles;
    }
public static List<CabeceraVenta> obtenerTodasCabeceras() {
    return new ArrayList<>(cabecerasVenta);
}

}