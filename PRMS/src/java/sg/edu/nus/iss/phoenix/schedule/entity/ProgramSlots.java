/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.schedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;

/**
 *
 * @author User
 */
public class ProgramSlots {

    private List<ProgramSlot> psList;

    public List<ProgramSlot> getPsList() {
        return psList;
    }

    public void setPsList(List<ProgramSlot> psList) {
        this.psList = psList;
    }

}
