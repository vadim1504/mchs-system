package by.mchs.controllers;

import by.mchs.exception.CallNotFound;
import by.mchs.model.Call;
import by.mchs.service.CallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/call")
public class CallController {

    @Autowired
    private CallService callServiceImpl;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE} )
    ResponseEntity<Void> addCall(@RequestBody Call call) {
        try {
            callServiceImpl.saveCall(call);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE} )
    ResponseEntity<List<Call>> getCalls() {
        List<Call> calls = callServiceImpl.allCall();
        if(calls.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(calls, HttpStatus.OK);
    }

    @GetMapping(params = {"id"},produces = {MediaType.APPLICATION_JSON_VALUE} )
    ResponseEntity<Call> getCallById(@RequestParam(value = "id") Integer id) {
        return callServiceImpl.getCall(id).map(msg -> new ResponseEntity<>(msg, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(params = {"id"})
    public ResponseEntity<Void> deleteCall(@RequestParam(value = "id") Integer id) {
        try {
            callServiceImpl.deleteCall(id);
        } catch (CallNotFound callNotFound) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<Void> updateCall(@RequestBody Call call) {
        try {
            callServiceImpl.updateCall(call);
        } catch (CallNotFound callNotFound) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
