package com.sap.cap.productsservice;

import com.sap.cds.services.cds.CdsCreateEventContext;
import com.sap.cds.services.cds.CdsReadEventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.On;
import com.sap.cds.services.handler.annotations.ServiceName;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ServiceName("AdminService")
public class AdminService implements EventHandler {

    private Map<Object, Map<String, Object>> products = new HashMap<>();

    @On(event = CdsService.EVENT_CREATE, entity = "AdminService.Products")
    public void onCreate(CdsCreateEventContext context) {
        context.getCqn().entries().forEach(entity -> products.put(entity.get("ID"), entity));
        context.setResult(context.getCqn().entries());
    }

    @On(event = CdsService.EVENT_READ, entity = "AdminService.Products")
    public void onRead(CdsReadEventContext context) {
        context.setResult(products.values());
    }

}
