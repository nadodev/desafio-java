package br.edu.unoesc.services;

import br.edu.unoesc.entities.Education;
import br.edu.unoesc.repositories.EducationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationService {

    @Autowired
    private EducationRepository educationRepository;

    public void save(Education education) {
        educationRepository.save(education);
    }

    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }
}