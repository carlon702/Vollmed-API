package med.voll.api.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import med.voll.api.domain.direction.DataDirection;
import med.voll.api.domain.mdData.*;
import med.voll.api.repository.MDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
@SuppressWarnings("all")
public class MDController {
    @Autowired
    private MDRepository mdRepository;

    @PostMapping
    public ResponseEntity<DataResponseMD> registerMD(@RequestBody @Valid DataMDRegister mdData, UriComponentsBuilder uriComponentsBuilder){
        MedicalDoctor medicalDoctor = mdRepository.save(new MedicalDoctor(mdData));
        DataResponseMD dataResponseMD = new DataResponseMD(
                medicalDoctor.getId(), medicalDoctor.getName(), medicalDoctor.getEmail(), medicalDoctor.getPhone(), medicalDoctor.getSpeciality().toString(), medicalDoctor.getDocument(),
                new DataDirection(medicalDoctor.getDirection().getStreet(), medicalDoctor.getDirection().getDistrict(), medicalDoctor.getDirection().getCity(), medicalDoctor.getDirection().getNumber(),
                        medicalDoctor.getDirection().getComplement()));
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medicalDoctor.getId()).toUri();
        return ResponseEntity.created(url).body(dataResponseMD);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponseMD> getMD(@PathVariable Long id){
        MedicalDoctor medicalDoctor = mdRepository.getReferenceById(id);
        DataResponseMD dataResponseMD = new DataResponseMD(medicalDoctor.getId(), medicalDoctor.getName(), medicalDoctor.getEmail(), medicalDoctor.getPhone(), medicalDoctor.getSpeciality().toString(), medicalDoctor.getDocument(), new DataDirection(medicalDoctor.getDirection().getStreet(), medicalDoctor.getDirection().getDistrict(), medicalDoctor.getDirection().getCity(), medicalDoctor.getDirection().getNumber(), medicalDoctor.getDirection().getComplement()));
        return ResponseEntity.ok(dataResponseMD);
    }

    @GetMapping
    public ResponseEntity<Page<MedicalDoctorListData>> medicalDoctorList(@PageableDefault(size=2) Pageable pagination){
       //return mdRepository.findAll(pagination).map(MedicalDoctorListData::new);
        return ResponseEntity.ok(mdRepository.findByActiveTrue(pagination).map(MedicalDoctorListData::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseMD> updateDoctor(@RequestBody @Valid DataUpdateMedicalDoctor dataUpdateMedicalDoctor){
        MedicalDoctor medicalDoctor = mdRepository.getReferenceById(dataUpdateMedicalDoctor.id());
        medicalDoctor.updateData(dataUpdateMedicalDoctor);
        return ResponseEntity.ok(new DataResponseMD(medicalDoctor.getId(), medicalDoctor.getName(), medicalDoctor.getEmail(), medicalDoctor.getPhone(), medicalDoctor.getSpeciality().toString(), medicalDoctor.getDocument(), new DataDirection(medicalDoctor.getDirection().getStreet(), medicalDoctor.getDirection().getDistrict(), medicalDoctor.getDirection().getCity(), medicalDoctor.getDirection().getNumber(), medicalDoctor.getDirection().getComplement())));
    }

//Soft Delete
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteMedicalDoctor(@PathVariable Long id){
        MedicalDoctor medicalDoctor = mdRepository.getReferenceById(id);
        medicalDoctor.deactivateDoctor();
        return ResponseEntity.noContent().build();
    }

//Hard delete
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void deleteMedicalDoctor(@PathVariable Long id){
//        MedicalDoctor medicalDoctor = mdRepository.getReferenceById(id);
//        mdRepository.delete(medicalDoctor);
//    }


}
