package by.mchs.service;

import by.mchs.exception.CallDuplicate;
import by.mchs.exception.CallNotFound;
import by.mchs.model.Call;

import java.util.List;
import java.util.Optional;

public interface CallService {

    List<Call> allCall();
    Optional<Call> getCall(int id);
    void saveCall(Call call) throws CallDuplicate;
    void updateCall(Call call) throws CallNotFound;
    void deleteCall(int id) throws CallNotFound;
    List<Call> getAllCallByOneUser();
}
