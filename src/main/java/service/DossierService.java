package service;

import db.DBService;
import org.macnss.Dossier;

public class DossierService extends DBService {

    public static void insertDossier(Dossier dossier){
        int patient_id = dossier.getPatient().getId();
    }
}
