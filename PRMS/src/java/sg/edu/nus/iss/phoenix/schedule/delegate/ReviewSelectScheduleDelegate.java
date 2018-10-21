package sg.edu.nus.iss.phoenix.schedule.delegate;

import java.util.List;
import sg.edu.nus.iss.phoenix.schedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.schedule.service.ReviewSelectScheduleService;

public class ReviewSelectScheduleDelegate {

    private ReviewSelectScheduleService service;

    public ReviewSelectScheduleDelegate() {
        service = new ReviewSelectScheduleService();
    }

    public List<ProgramSlot> reviewSelectSchedule() {
        return service.reviewSelectSchedule();
    }

}
