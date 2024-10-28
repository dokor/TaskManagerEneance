package fr.lelouet.taskmanagereneance.webservices.utils.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSession {
    private final String webSessionToken;
    private final long refreshDurationInMillis;
    private final long inactiveDurationInMillis;


    public UserSession(String webSessionToken) {
        this.webSessionToken = webSessionToken;
        // Par soucis de simplicité, les durées ne sont volontairement pas placé en configuration applicative
        this.refreshDurationInMillis = 60000L; // 1 minute
        this.inactiveDurationInMillis = 3600000L; // 1 heure
    }
}