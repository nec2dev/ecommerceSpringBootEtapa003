package com.nec2solutions.ecommercespringbootetapas.controller;
import com.nec2solutions.ecommercespringbootetapas.model.Orden;
import com.nec2solutions.ecommercespringbootetapas.model.Producto;
import com.nec2solutions.ecommercespringbootetapas.service.DetalleOrdenService;
import com.nec2solutions.ecommercespringbootetapas.service.OrdenService;
import com.nec2solutions.ecommercespringbootetapas.service.ProductoService;
import com.nec2solutions.ecommercespringbootetapas.model.DetalleOrden;
import com.nec2solutions.ecommercespringbootetapas.service.UsuarioService;
import com.nec2solutions.ecommercespringbootetapas.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductoService productoService;
    private final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private OrdenService ordenService;
    @Autowired
    private DetalleOrdenService detalleOrdenService;
    @Autowired
    private UsuarioService usuarioService;
    List<DetalleOrden> detalles = new ArrayList<>();
    Orden orden = new Orden();

    @GetMapping("")
    public String home(Model model) {
        List<Producto> productos = productoService.findAll();
        model.addAttribute("productos", productos);
        return "/home";
    }

    @GetMapping("producto/{id}")
    public String productoHome(@PathVariable Integer id, Model model) {
        log.info("Id producto enviado como parámetro {}", id);
        Producto producto;
        Optional<Producto> productoOptional = productoService.get(id);
        producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "pages/itemdetail";
    }

    @GetMapping("/carrito")
    public String getCart() {
        return "pages/cart";
    }

    @PostMapping("/carrito")
    public String addCart(@RequestParam Integer id, @RequestParam Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto;
        double sumaTotal;
        Optional<Producto> optionalProducto = productoService.get(id);
        log.info("Producto añadido: {}", optionalProducto.get());
        log.info("Cantidad: {}", cantidad);
        producto = optionalProducto.get();
        detalleOrden.setCantidad(cantidad);
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        Integer idProducto=producto.getId();
        boolean ingresado=detalles.stream().anyMatch(p -> Objects.equals(p.getProducto().getId(), idProducto));
        if (!ingresado) {
            detalles.add(detalleOrden);
        }
        sumaTotal = detalles.stream().mapToDouble(DetalleOrden::getTotal).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "pages/cart";
    }

    @GetMapping("/delete/cart/{id}")
    public String deleteProductoCart(@PathVariable Integer id, Model model) {
        List<DetalleOrden> ordenesNueva = new ArrayList<>();
        for (DetalleOrden detalleOrden : detalles) {
            if (!Objects.equals(detalleOrden.getProducto().getId(), id)) {
                ordenesNueva.add(detalleOrden);
            }
        }
        detalles = ordenesNueva;
        double sumaTotal;
        sumaTotal = detalles.stream().mapToDouble(DetalleOrden::getTotal).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        return "pages/cart";
    }

    @GetMapping("/orden")
    public String orderDetail(Model model) {
        Usuario usuario = usuarioService.findById(10003).get();
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
        return "pages/orderdetail";
    }

    @GetMapping("/guardarorden")
    public String saveOrder() {
        Date fechaCreacion = new Date();
        orden.setFechaCreacion(fechaCreacion);
        orden.setNumero(ordenService.generarNumeroOrden());
        Usuario usuario = usuarioService.findById(10003).get();
        orden.setUsuario(usuario);
        ordenService.save(orden);
        for (DetalleOrden detalleOrden:detalles) {
            detalleOrden.setOrden(orden);
            detalleOrdenService.save(detalleOrden);
        }
        orden = new Orden();
        detalles.clear();
        return "redirect:/";
    }
    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model) {
        log.info("Nombre enviado como parámetro {}", nombre);
        List<Producto> productos = productoService.findAll().stream().filter(p -> p.getNombre().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("productos", productos);
        return "usuario/home";
    }
}
