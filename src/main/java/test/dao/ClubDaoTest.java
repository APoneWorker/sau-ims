package test.dao;

import com.fekpal.dao.ClubDao;
import com.fekpal.dao.UserDao;
import com.fekpal.domain.Club;
import com.fekpal.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

import static test.dao.Domain.club;
import static test.dao.Domain.user;

/**
 * Created by APone on 2017/8/21.
 */
public class ClubDaoTest extends BaseDaoTest {

    @Autowired
    ClubDao clubDao;

    @Autowired
    UserDao userDao;

    @Before
    public void init() {
        userDao.addUser(user);
        club.setUserId(user.getUserId());
        clubDao.addClub(club);
    }

    @Test
    public void testClubDao() {

        Assert.assertNull(clubDao.getClubByClubId(10));
        System.out.println((club = clubDao.getClubByClubId(club.getClubId())).toString());
        Assert.assertTrue(!clubDao.findClubByClubName("T社", 0, 2).isEmpty());
        Assert.assertTrue(clubDao.findClubByClubName("zj", 0, 2).isEmpty());


        System.out.println(clubDao.getClubByClubName(club.getClubName()).toString());
        Assert.assertNull(clubDao.getClubByClubName("asd"));
        Assert.assertNotNull(clubDao.getClubByUserId(user.getUserId()));

        user.setUserId(1);
        Assert.assertNull(clubDao.getClubByUserId(user.getUserId()));

        user.setUserId(club.getUserId());
        Assert.assertNotNull(clubDao.getClubByUserId(user.getUserId()));

        clubDao.updateLikeNumber(club.getClubId());
        clubDao.updateLikeNumber(club.getClubId());
        clubDao.updateLikeNumber(club.getClubId());
        clubDao.updateLikeNumber(club.getClubId());

        System.out.println(club.toString());
        club = clubDao.getClubByUserId(user.getUserId());
        System.out.println(club.toString());

        Assert.assertFalse(clubDao.hadClubName("ip"));
        Assert.assertTrue(clubDao.hadClubName("IT社"));

        Assert.assertTrue(!clubDao.loadAllClub(0, 2).isEmpty());

        club.setClubName("呵呵社");
        club.setDescription("注解在此");
        club.setMembers(club.getMembers() + 1);
        clubDao.updateClub(club);

        Assert.assertNotNull(clubDao.getClubByClubName("呵呵社"));
        Assert.assertNull(clubDao.getClubByClubName("IT社"));
        System.out.println(clubDao.getClubByClubName("呵呵社").toString());
    }

    public ClubDao getClubDao() {
        return clubDao;
    }

    public void setClubDao(ClubDao clubDao) {
        this.clubDao = clubDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
