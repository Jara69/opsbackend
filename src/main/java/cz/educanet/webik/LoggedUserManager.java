package cz.educanet.webik;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class LoggedUserManager implements Serializable {
    User user = null;
}
