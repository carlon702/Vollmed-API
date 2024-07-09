package med.voll.api.repository;

import med.voll.api.domain.mdData.MedicalDoctor;
import med.voll.api.domain.mdData.Speciality;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;


@Repository
public interface MDRepository extends JpaRepository<MedicalDoctor, Long> {

    Page<MedicalDoctor> findByActiveTrue(Pageable pagination);


@Query("""
            select m from MD m
            where m.active=true
            and
            m.speciality=:speciality
            and
            m.id not in(
                select c.medicalDoctor.id from Consultation c
                where
                c.dateTime=:dateTime
            )
            order by rand()
            limit 1
            """)
MedicalDoctor selectMDWithSpecialityWithDate(Speciality speciality, LocalDateTime dateTime);

    @Query("""
            select m.active
            from MD m
            where m.id=:idMD
            """)
    Boolean findActiveById(Long idMD);
}
