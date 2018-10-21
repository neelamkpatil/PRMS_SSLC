package sg.edu.nus.iss.phoenix.schedule.entity;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

public class ProgramSlot implements Cloneable, Serializable {

    /**
     * eclipse identifier
     */
    // private static final long serialVersionUID = -5500218812568593553L;
    /**
     * Persistent Instance variables. This data is directly mapped to the
     * columns of database table.
     */
    private int id = -1;
    private String rpname;
    private Date date;
    private Time sttime;
    private Time duration;
    private String presenter;
    private String producer;

    /**
     * Constructors. The first one takes no arguments and provides the most
     * simple way to create object instance. The another one takes one argument,
     * which is the primary key of the corresponding table.
     */
    public ProgramSlot() {

    }

    public ProgramSlot(int id) {
        this.id = id;
    }

    public ProgramSlot(String rpname, Date date, Time sttime, Time duration, String presenter, String producer) {
        this.rpname = rpname;
        this.date = date;
        this.sttime = sttime;
        this.duration = duration;
        this.presenter = presenter;
        this.producer = producer;
    }

    public ProgramSlot(int id, String rpname, Date date, Time sttime, Time duration, String presenter, String producer) {
        this.id = id;
        this.rpname = rpname;
        this.date = date;
        this.sttime = sttime;
        this.duration = duration;
        this.presenter = presenter;
        this.producer = producer;
    }

    public ProgramSlot(String rpname, Time duration, String presenter, String producer) {
        this.rpname = rpname;
        this.duration = duration;
        this.presenter = presenter;
        this.producer = producer;
    }

    /**
     * Get- and Set-methods for persistent variables. The default behaviour does
     * not make any checks against malformed data, so these might require some
     * manual additions.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRpname() {
        return rpname;
    }

    public void setRpname(String rpname) {
        this.rpname = rpname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getSttime() {
        return sttime;
    }

    public void setSttime(Time sttime) {
        this.sttime = sttime;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public String getPresenter() {
        return presenter;
    }

    public void setPresenter(String presenter) {
        this.presenter = presenter;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * setAll allows to set all persistent variables in one method call. This is
     * useful, when all data is available and it is needed to set the initial
     * state of this object. Note that this method will directly modify instance
     * variables, without going trough the individual set-methods.
     *
     * @param rpnameIn
     * @param dateIn
     * @param sttimeIn
     * @param durationIn
     * @param presenterIn
     * @param producerIn
     */
    public void setAll(int idIn, String rpnameIn, Date dateIn, Time sttimeIn, Time durationIn, String presenterIn, String producerIn) {
        this.id = idIn;
        this.rpname = rpnameIn;
        this.date = dateIn;
        this.sttime = sttimeIn;
        this.duration = durationIn;
        this.presenter = presenterIn;
        this.producer = producerIn;
    }

    /**
     * hasEqualMapping-method will compare two ProgramSlot instances and return
     * true if they contain same values in all persistent instance variables. If
     * hasEqualMapping returns true, it does not mean the objects are the same
     * instance. However it does mean that in that moment, they are mapped to
     * the same row in database.
     *
     * @param valueObject
     * @return
     */
    public boolean hasEqualMapping(ProgramSlot valueObject) {

        // need to implement
        if (this.id == -1) {
            if (valueObject.getId() != -1) {
                return (false);
            }
        } else if (this.id != valueObject.getId()) {
            return (false);
        }
        if (this.rpname == null) {
            if (valueObject.getRpname() != null) {
                return (false);
            }
        } else if (!this.rpname.equals(valueObject.getRpname())) {
            return (false);
        }
        if (this.date == null) {
            if (valueObject.getDate() != null) {
                return (false);
            }
        } else if (!this.date.equals(valueObject.getDate())) {
            return (false);
        }
        if (this.sttime == null) {
            if (valueObject.getSttime() != null) {
                return (false);
            }
        } else if (!this.sttime.equals(valueObject.getSttime())) {
            return (false);
        }
        if (this.duration == null) {
            if (valueObject.getDuration() != null) {
                return (false);
            }
        } else if (!this.duration.equals(valueObject.getDuration())) {
            return (false);
        }
        if (this.presenter == null) {
            if (valueObject.getPresenter() != null) {
                return (false);
            }
        } else if (!this.presenter.equals(valueObject.getPresenter())) {
            return (false);
        }
        if (this.producer == null) {
            if (valueObject.getProducer() != null) {
                return (false);
            }
        } else if (!this.producer.equals(valueObject.getProducer())) {
            return (false);
        }

        return true;
    }

    /**
     * toString will return String object representing the state of this
     * valueObject. This is useful during application development, and possibly
     * when application is writing object states in text log.
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("\nProgramSlot class, mapping to table program-slot\n");
        out.append("Persistent attributes: \n");
        out.append("id = ").append(this.id).append("\n");
        out.append("rpname = ").append(this.rpname).append("\n");
        out.append("date = ").append(this.date).append("\n");
        out.append("sttime = ").append(this.sttime).append("\n");
        out.append("duration = ").append(this.duration).append("\n");
        out.append("presenter = ").append(this.presenter).append("\n");
        out.append("producer = ").append(this.producer).append("\n");
        return out.toString();
    }

    /**
     * Clone will return identical deep copy of this valueObject. Note, that
     * this method is different than the clone() which is defined in
     * java.lang.Object. Here, the returned cloned object will also have all its
     * attributes cloned.
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        ProgramSlot cloned = new ProgramSlot();

        if (this.id != -1) {
            cloned.setId(this.id);
        }
        if (this.rpname != null) {
            cloned.setRpname(this.rpname);
        }
        if (this.date != null) {
            cloned.setDate((Date) this.date.clone());
        }
        if (this.sttime != null) {
            cloned.setSttime((Time) this.sttime.clone());
        }
        if (this.duration != null) {
            cloned.setDuration((Time) this.duration.clone());
        }
        if (this.presenter != null) {
            cloned.setPresenter(this.presenter);
        }
        if (this.producer != null) {
            cloned.setProducer(this.producer);
        }
        return cloned;
    }

}
