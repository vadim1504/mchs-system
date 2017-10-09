package by.mchs.service;

import by.mchs.exception.CallDuplicate;
import by.mchs.exception.CallNotFound;
import by.mchs.model.Call;
import by.mchs.reposotory.CallRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class CallServiceImpl implements CallService {

    @Resource
    private CallRepository callRepository;

    @Override
    @Transactional
    public List<Call> allCall() {
        return callRepository.findAll();
    }

    @Override
    @Transactional
    public Call getCall(int id) {
        return callRepository.findOne(id);
    }

    @Override
    @Transactional(rollbackFor=CallDuplicate.class)
    public void saveCall(Call call) throws CallDuplicate {
        if(callRepository.existsByIdCall(call.getIdCall(),call.getIdTower())){
            throw new CallDuplicate();
        }
        callRepository.save(call);
    }

    @Override
    @Transactional(rollbackFor=CallNotFound.class)
    public void updateCall(Call call) throws CallNotFound {
        Call updateCall = callRepository.getOne(call.getId());
        if(updateCall==null)
            throw new CallNotFound();
        updateCall.setDistance(call.getDistance());
        updateCall.setIdTower(call.getIdTower());
    }

    @Override
    @Transactional(rollbackFor=CallNotFound.class)
    public void deleteCall(int id) throws CallNotFound {
        Call deleteCall = callRepository.getOne(id);
        if(deleteCall==null)
            throw new CallNotFound();
        callRepository.delete(deleteCall);
    }

    @Override
    @Transactional()
    public List<Call> getAllCallByOneUser() {
        return callRepository.getAllCallByOneUser();
    }
}
