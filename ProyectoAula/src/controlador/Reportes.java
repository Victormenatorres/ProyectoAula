package controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.Producto;
import modelo.Categoria;
import modelo.CabeceraVenta;
import modelo.DetalleVenta;

public class Reportes {
    private Ctrl_Cliente ctrlCliente = new Ctrl_Cliente();
    private Ctrl_Producto ctrlProducto = new Ctrl_Producto();
    private Ctrl_Categoria ctrlCategoria = new Ctrl_Categoria();
    private Ctrl_RegistrarVenta ctrlVenta = new Ctrl_RegistrarVenta();

    /* ********************************************************************
    * Reporte de clientes registrados en el sistema
    *********************************************************************** */
    public void ReportesClientes() {
        try {
            Document documento = new Document();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Clientes.pdf"));
            
            // Configurar encabezado
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            // Configurar párrafo
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Clientes \n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            // Crear tabla
            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Codigo");
            tabla.addCell("Nombres");
            tabla.addCell("Cedula");
            tabla.addCell("Telefono");
            tabla.addCell("Direccion");

            // Obtener clientes de la lista
            List<Cliente> clientes = ctrlCliente.obtenerTodosClientes();
            
            for (Cliente cliente : clientes) {
                tabla.addCell(String.valueOf(cliente.getIdCliente()));
                tabla.addCell(cliente.getNombre() + " " + cliente.getApellido());
                tabla.addCell(cliente.getCedula());
                tabla.addCell(cliente.getTelefono());
                tabla.addCell(cliente.getDireccion());
            }

            documento.add(tabla);
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte de clientes creado exitosamente");

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al crear reporte: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /* ********************************************************************
    * Reporte de productos registrados en el sistema
    *********************************************************************** */
    public void ReportesProductos() {
        try {
            Document documento = new Document();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Productos.pdf"));
            
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Productos \n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 5, 4, 5, 7, 5, 6};
            PdfPTable tabla = new PdfPTable(columnsWidths);
            
            tabla.addCell("Codigo");
            tabla.addCell("Nombre");
            tabla.addCell("Cant.");
            tabla.addCell("Precio");
            tabla.addCell("Descripcion");
            tabla.addCell("Por. Iva");
            tabla.addCell("Categoria");

            List<Producto> productos = ctrlProducto.obtenerTodosProductos();
            
            for (Producto producto : productos) {
                tabla.addCell(String.valueOf(producto.getIdProducto()));
                tabla.addCell(producto.getNombre());
                tabla.addCell(String.valueOf(producto.getCantidad()));
                tabla.addCell(String.valueOf(producto.getPrecio()));
                tabla.addCell(producto.getDescripcion());
                tabla.addCell(String.valueOf(producto.getPorcentajeIva()) + "%");
                tabla.addCell(ctrlCategoria.obtenerNombreCategoriaPorId(producto.getIdCategoria()));
            }

            documento.add(tabla);
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte de productos creado exitosamente");

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al crear reporte: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /* ********************************************************************
    * Reporte de categorías registradas en el sistema
    *********************************************************************** */
    public void ReportesCategorias() {
        try {
            Document documento = new Document();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Categorias.pdf"));
            
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Categorias \n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);

            PdfPTable tabla = new PdfPTable(3);
            tabla.addCell("Codigo");
            tabla.addCell("Descripcion");
            tabla.addCell("Estado");

            List<Categoria> categorias = ctrlCategoria.obtenerTodasCategorias();
            
            for (Categoria categoria : categorias) {
                tabla.addCell(String.valueOf(categoria.getIdCategoria()));
                tabla.addCell(categoria.getDescripcion());
                tabla.addCell(categoria.getEstado() == 1 ? "Activo" : "Inactivo");
            }

            documento.add(tabla);
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte de categorías creado exitosamente");

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al crear reporte: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /* ********************************************************************
    * Reporte de ventas registradas en el sistema
    *********************************************************************** */
    public void ReportesVentas() {
        try {
            Document documento = new Document();
            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Reporte_Ventas.pdf"));
            
            Image header = Image.getInstance("src/img/header1.jpg");
            header.scaleToFit(650, 1000);
            header.setAlignment(Chunk.ALIGN_CENTER);
            
            Paragraph parrafo = new Paragraph();
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            parrafo.add("Reporte creado por \nEdison Zambrano © Programador Fantasma\n\n");
            parrafo.setFont(FontFactory.getFont("Tahoma", 18, Font.BOLD, BaseColor.DARK_GRAY));
            parrafo.add("Reporte de Ventas \n\n");

            documento.open();
            documento.add(header);
            documento.add(parrafo);
            
            float[] columnsWidths = {3, 9, 4, 5, 3};
            PdfPTable tabla = new PdfPTable(columnsWidths);
            
            tabla.addCell("Codigo");
            tabla.addCell("Cliente");
            tabla.addCell("Tot. Pagar");
            tabla.addCell("Fecha Venta");
            tabla.addCell("Estado");

            List<CabeceraVenta> ventas = ctrlVenta.obtenerTodasCabeceras();
            
            for (CabeceraVenta venta : ventas) {
                Cliente cliente = ctrlCliente.obtenerClientePorId(venta.getIdCliente());
                String nombreCliente = (cliente != null) ? 
                    cliente.getNombre() + " " + cliente.getApellido() : "Cliente no encontrado";
                
                tabla.addCell(String.valueOf(venta.getIdCabeceraVenta()));
                tabla.addCell(nombreCliente);
                tabla.addCell(String.valueOf(venta.getValorPagar()));
                tabla.addCell(venta.getFechaVenta());
                tabla.addCell(venta.getEstado() == 1 ? "Activo" : "Inactivo");
            }

            documento.add(tabla);
            documento.close();
            
            JOptionPane.showMessageDialog(null, "Reporte de ventas creado exitosamente");

        } catch (DocumentException | IOException e) {
            JOptionPane.showMessageDialog(null, 
                "Error al crear reporte: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}