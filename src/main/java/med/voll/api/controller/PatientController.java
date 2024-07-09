package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.DataPatientList;
import med.voll.api.domain.patient.DataRegisterPatient;
import med.voll.api.domain.patient.DataUpdatePatient;
import med.voll.api.domain.patient.Patient;
import med.voll.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class PatientController {
    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterPatient dataRegisterPatient) {
        repository.save(new Patient(dataRegisterPatient));
    }

    @GetMapping
    public Page<DataPatientList> list(@PageableDefault(size = 10) Pageable pagination) {
        return repository.findAllByActiveTrue(pagination).map(DataPatientList::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid DataUpdatePatient dataUpdatePatient) {
        var patient = repository.getReferenceById(dataUpdatePatient.id());
        patient.updateInformation(dataUpdatePatient);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
    }

}
