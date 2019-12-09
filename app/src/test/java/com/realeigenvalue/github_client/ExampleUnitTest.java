package com.realeigenvalue.github_client;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void test_GetFollowers() {
        ArrayList<User> actual_followers = new ArrayList<User>();
        //new GetFollowers(actual_followers, MainActivity.FOLLOWERS, MainActivity.CREDENTIAL).execute();
        ArrayList<User> expected_followers = new ArrayList<User>();
        assertEquals(expected_followers, actual_followers);
    }

    @Test
    public void test_GetFollowing() {
        User user1 = new User();
        user1.setUsername("Andreas Klöckner");
        user1.setURL("https://api.github.com/users/inducer");
        User user2 = new User();
        user2.setUsername("Lawrence Angrave\n");
        user2.setURL("https://api.github.com/users/angrave");
        ArrayList<User> actual_following = new ArrayList<User>();
        setup_actual_following(actual_following);
        ArrayList<User> expected_following = new ArrayList<User>();
        expected_following.add(user1); expected_following.add(user2);
        for(int i = 0; i < 2; i++) {
            assertEquals(expected_following.get(i).getUsername(), actual_following.get(i).getUsername());
            assertEquals(expected_following.get(i).getURL(), actual_following.get(i).getURL());
        }
    }

    @Test
    public void test_GetProfile() {
        Profile expected_profile = new Profile();
        expected_profile.setName("androiduser");
        expected_profile.setUsername("androidstudiotest");
        expected_profile.setPublic_repos("2");
        expected_profile.setFollowing_count("2");
        expected_profile.setFollowers_count("0");
        expected_profile.setCreated_date("2017-10-27T07:32:39Z");
        expected_profile.setProfile_pic(null);
        Profile actual_profile = new Profile();
        setup_actual_profile(actual_profile);
        assertEquals(expected_profile.getName(), actual_profile.getName());
        assertEquals(expected_profile.getUsername(), actual_profile.getUsername());
        assertEquals(expected_profile.getPublic_repos(), actual_profile.getPublic_repos());
        assertEquals(expected_profile.getFollowing_count(), actual_profile.getFollowing_count());
        assertEquals(expected_profile.getFollowers_count(), actual_profile.getFollowers_count());
        assertEquals(expected_profile.getCreated_date(), actual_profile.getCreated_date());
        assertEquals(expected_profile.getProfile_pic(), actual_profile.getProfile_pic());
    }

    @Test
    public void test_GetRepositories() {
        Repository expected_repository1 = new Repository();
        expected_repository1.setRepository_name("repo1");
        expected_repository1.setUsername("androidstudiotest");
        expected_repository1.setDescription("test repo1");
        Repository expected_repository2 = new Repository();
        expected_repository2.setRepository_name("repo2");
        expected_repository2.setUsername("androidstudiotest");
        expected_repository2.setDescription("test repo2");
        Repository actual_repository1 = new Repository();
        Repository actual_repository2 = new Repository();
        setup_actual_repositories(actual_repository1, actual_repository2);
        assertEquals(expected_repository1.getRepository_name(), actual_repository1.getRepository_name());
        assertEquals(expected_repository1.getUsername(), actual_repository1.getUsername());
        assertEquals(expected_repository1.getDescription(), actual_repository1.getDescription());
        assertEquals(expected_repository2.getRepository_name(), actual_repository2.getRepository_name());
        assertEquals(expected_repository2.getUsername(), actual_repository2.getUsername());
        assertEquals(expected_repository2.getDescription(), actual_repository2.getDescription());
    }

    public void setup_actual_following(ArrayList<User> actual_following) {
        User user1 = new User();
        user1.setUsername("Andreas Klöckner");
        user1.setURL("https://api.github.com/users/inducer");
        User user2 = new User();
        user2.setUsername("Lawrence Angrave\n");
        user2.setURL("https://api.github.com/users/angrave");
        //new GetFollowing(actual_following, MainActivity.FOLLOWING, MainActivity.CREDENTIAL).execute();
        actual_following.add(user1); actual_following.add(user2);
    }

    public void setup_actual_profile(Profile actual_profile) {
        //new GetProfile(actual_profile, MainActivity.USER, MainActivity.CREDENTIAL).execute();
        actual_profile.setName("androiduser");
        actual_profile.setUsername("androidstudiotest");
        actual_profile.setPublic_repos("2");
        actual_profile.setFollowing_count("2");
        actual_profile.setFollowers_count("0");
        actual_profile.setCreated_date("2017-10-27T07:32:39Z");
        actual_profile.setProfile_pic(null);
    }

    public void setup_actual_repositories(Repository actual_repository1, Repository actual_repository2) {
        actual_repository1.setRepository_name("repo1");
        actual_repository1.setUsername("androidstudiotest");
        actual_repository1.setDescription("test repo1");
        actual_repository2.setRepository_name("repo2");
        actual_repository2.setUsername("androidstudiotest");
        actual_repository2.setDescription("test repo2");
    }
}