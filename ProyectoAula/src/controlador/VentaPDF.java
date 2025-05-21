package controlador;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import vista.InterFacturacion;

public class VentaPDF {
    private String totalPagar;

public void setTotalPagar(String total) {
    this.totalPagar = total;
}


    private String nombreCliente;
    private String cedulaCliente;
    private String telefonoCliente;
    private String direccionCliente;

    private String fechaActual = "";
    private String nombreArchivoPDFVenta = "";

    // Método para establecer datos del cliente desde una lista (simulación)
    public void setDatosClienteDesdeLista(String cedula, String nombreCompleto, String telefono, String direccion) {
        this.cedulaCliente = cedula;
        this.nombreCliente = nombreCompleto;
        this.telefonoCliente = telefono;
        this.direccionCliente = direccion;
    }

    // Método para generar la factura de venta
    public void generarFacturaPDF() {
        try {
            // Obtener la fecha actual
            Date date = new Date();
            fechaActual = new SimpleDateFormat("yyyy/MM/dd").format(date);
            String fechaNueva = fechaActual.replace("/", "_");

            nombreArchivoPDFVenta = "Venta_" + nombreCliente + "_" + fechaNueva + ".pdf";
            File file = new File("src/pdf/" + nombreArchivoPDFVenta);
            FileOutputStream archivo = new FileOutputStream(file);

            Document doc = new Document();
            PdfWriter.getInstance(doc, archivo);
            doc.open();

            Image img = Image.getInstance("src/img/ventas.png");

            Paragraph fecha = new Paragraph();
            Font negrita = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLUE);
            fecha.add(Chunk.NEWLINE);
            fecha.add("Factura: 001\nFecha: " + fechaActual + "\n\n");

            // Encabezado
            PdfPTable encabezado = new PdfPTable(4);
            encabezado.setWidthPercentage(100);
            encabezado.getDefaultCell().setBorder(0);
            float[] columnaEncabezado = new float[]{20f, 30f, 70f, 40f};
            encabezado.setWidths(columnaEncabezado);
            encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);

            encabezado.addCell(img);

            String ruc = "0987654321001";
            String nombre = "Fantasma Cooporation";
            String telefono = "0987654321";
            String direccion = "Tamarindo City";
            String razon = "La magia de la programación está en el poder de tu imaginación";

            encabezado.addCell("");
            encabezado.addCell("RUC: " + ruc + "\nNOMBRE: " + nombre + "\nTELEFONO: " + telefono + "\nDIRECCION: " + direccion + "\nRAZON SOCIAL: " + razon);
            encabezado.addCell(fecha);
            doc.add(encabezado);

            // Datos del cliente
            Paragraph cliente = new Paragraph();
            cliente.add(Chunk.NEWLINE);
            cliente.add("Datos del cliente:\n\n");
            doc.add(cliente);

            PdfPTable tablaCliente = new PdfPTable(4);
            tablaCliente.setWidthPercentage(100);
            tablaCliente.getDefaultCell().setBorder(0);
            float[] columnaCliente = new float[]{25f, 45f, 30f, 40f};
            tablaCliente.setWidths(columnaCliente);
            tablaCliente.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell c1 = new PdfPCell(new Phrase("Cedula/RUC: ", negrita));
            PdfPCell c2 = new PdfPCell(new Phrase("Nombre: ", negrita));
            PdfPCell c3 = new PdfPCell(new Phrase("Telefono: ", negrita));
            PdfPCell c4 = new PdfPCell(new Phrase("Direccion: ", negrita));
            c1.setBorder(0); c2.setBorder(0); c3.setBorder(0); c4.setBorder(0);
            tablaCliente.addCell(c1);
            tablaCliente.addCell(c2);
            tablaCliente.addCell(c3);
            tablaCliente.addCell(c4);
            tablaCliente.addCell(cedulaCliente);
            tablaCliente.addCell(nombreCliente);
            tablaCliente.addCell(telefonoCliente);
            tablaCliente.addCell(direccionCliente);
            doc.add(tablaCliente);

            // Espacio en blanco
            Paragraph espacio = new Paragraph();
            espacio.add(Chunk.NEWLINE);
            espacio.setAlignment(Element.ALIGN_CENTER);
            doc.add(espacio);

            // Tabla de productos
            PdfPTable tablaProducto = new PdfPTable(4);
            tablaProducto.setWidthPercentage(100);
            tablaProducto.getDefaultCell().setBorder(0);
            float[] columnaProducto = new float[]{15f, 50f, 15f, 20f};
            tablaProducto.setWidths(columnaProducto);
            tablaProducto.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell p1 = new PdfPCell(new Phrase("Cantidad", negrita));
            PdfPCell p2 = new PdfPCell(new Phrase("Descripción", negrita));
            PdfPCell p3 = new PdfPCell(new Phrase("Precio Unitario", negrita));
            PdfPCell p4 = new PdfPCell(new Phrase("Precio Total", negrita));
            p1.setBorder(0); p2.setBorder(0); p3.setBorder(0); p4.setBorder(0);
            p1.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p2.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p3.setBackgroundColor(BaseColor.LIGHT_GRAY);
            p4.setBackgroundColor(BaseColor.LIGHT_GRAY);
            tablaProducto.addCell(p1);
            tablaProducto.addCell(p2);
            tablaProducto.addCell(p3);
            tablaProducto.addCell(p4);

            for (int i = 0; i < InterFacturacion.jTable_productos.getRowCount(); i++) {
                String producto = InterFacturacion.jTable_productos.getValueAt(i, 1).toString();
                String cantidad = InterFacturacion.jTable_productos.getValueAt(i, 2).toString();
                String precio = InterFacturacion.jTable_productos.getValueAt(i, 3).toString();
                String total = InterFacturacion.jTable_productos.getValueAt(i, 7).toString();

                tablaProducto.addCell(cantidad);
                tablaProducto.addCell(producto);
                tablaProducto.addCell(precio);
                tablaProducto.addCell(total);
            }

            doc.add(tablaProducto);

            // Total a pagar
            Paragraph info = new Paragraph();
            info.add(Chunk.NEWLINE);
          info.add("Total a pagar: " + totalPagar);
            info.setAlignment(Element.ALIGN_RIGHT);
            doc.add(info);

            // Firma
            Paragraph firma = new Paragraph();
            firma.add(Chunk.NEWLINE);
            firma.add("Cancelación y firma\n\n");
            firma.add("_______________________");
            firma.setAlignment(Element.ALIGN_CENTER);
            doc.add(firma);

            // Mensaje de agradecimiento
            Paragraph mensaje = new Paragraph();
            mensaje.add(Chunk.NEWLINE);
            mensaje.add("¡Gracias por su compra!");
            mensaje.setAlignment(Element.ALIGN_CENTER);
            doc.add(mensaje);

            // Cerrar documento y abrir archivo
            doc.close();
            archivo.close();
            Desktop.getDesktop().open(file);

        } catch (DocumentException | IOException e) {
            System.out.println("Error al generar PDF: " + e);
        }
    }
}
