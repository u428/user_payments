package com.ssd.ulugbek.income.Controller;

import com.ssd.ulugbek.income.Model.Reqs.ReqsPayment;
import com.ssd.ulugbek.income.Model.Reqs.ReqsTypesPayment;
import com.ssd.ulugbek.income.Service.PaymentService;
import com.ssd.ulugbek.income.Service.TypesOfPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/ssd/rest-api")
public class UnitController {

    @Autowired
    TypesOfPaymentService typesOfPaymentService;

    @Autowired
    PaymentService paymentService;


    @GetMapping(path = "/hello")
    public String hello(){
        return "helo";
    }

    @GetMapping(path = "/")
    public ResponseEntity<String> Hello(@RequestParam("start") Date start,@RequestParam("end") Date end ){
        return ResponseEntity.ok(paymentService.getSummAmount(start, end));
    }

    @PostMapping(path = "/postPayment")
    public ResponseEntity<String> postPayment(@RequestBody ReqsPayment reqsPayment){
        try {
            paymentService.postPayment(reqsPayment, "email");
        return ResponseEntity.ok("Success");
        }catch (Exception e){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.ALREADY_REPORTED);
        }
    }


    @GetMapping(path = "/getAllTypesOfPayment")
    public ResponseEntity<List<ReqsTypesPayment>> getAllTypesOfPayment(){
        return ResponseEntity.ok(typesOfPaymentService.getAllTypesOfPayment("hello"));
    }

    @DeleteMapping(path = "/deleteTypesOfPayment/{name}")
    public ResponseEntity<String> deleteTypesOfPayment(@PathVariable("name") String name){

        return ResponseEntity.ok(typesOfPaymentService.deleteTypesOfPayment(name, "hello"));
    }

    @PutMapping(path = "/putTypesOfPayment")
    public ResponseEntity<ReqsTypesPayment> putTypesOfpayment(@RequestBody ReqsTypesPayment reqsTypesPayment){
        return ResponseEntity.ok(typesOfPaymentService.putTypesOfPayment(reqsTypesPayment, "helo"));
    }

    @PostMapping(path = "/postTypesOfPayment")
    public ResponseEntity<String> postTpesOfPayment(@RequestBody ReqsTypesPayment reqsTypesPayment){
        try {
            typesOfPaymentService.saveTypesOfPayment(reqsTypesPayment, "hello");
            return ResponseEntity.ok("Success");
        }catch (Exception e){
            return (ResponseEntity<String>) ResponseEntity.status(HttpStatus.ALREADY_REPORTED);
        }
    }

    


}
