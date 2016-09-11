package com.haibin.elegantproject.model;


import java.io.Serializable;

/**
 * 用户信息
 * Created by huanghaibin_dev
 * on 2016/4/11.
 */
@SuppressWarnings("unused")
public class User implements Serializable {
    private int VIP;

    private String avatarUrl;

    private String backgroundUrl;

    private int buyVIPCount;

    private String createUserId;

    private String email;

    private int id;

    private int isBanPasserby;

    private int isInvalid;

    private int isPrivacy;

    private int isPublic;

    private int isReview;

    private int isTouchID;

    private String lastDiaryDate;

    private String lastLoginDate;

    private String loginPwd;

    private String nickName;

    private String privacyPwd;

    private String registerDate;

    private String signature;

    private int starCount;

    private String vipEndDate;

    private int vipType;

    private int writeUpCount;

    private int notificationId;

    private int announcementId;

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public int getVIP() {
        return VIP;
    }

    public void setVIP(int VIP) {
        this.VIP = VIP;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBackgroundUrl() {
        return backgroundUrl;
    }

    public void setBackgroundUrl(String backgroundUrl) {
        this.backgroundUrl = backgroundUrl;
    }

    public int getBuyVIPCount() {
        return buyVIPCount;
    }

    public void setBuyVIPCount(int buyVIPCount) {
        this.buyVIPCount = buyVIPCount;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsBanPasserby() {
        return isBanPasserby;
    }

    public void setIsBanPasserby(int isBanPasserby) {
        this.isBanPasserby = isBanPasserby;
    }

    public int getIsInvalid() {
        return isInvalid;
    }

    public void setIsInvalid(int isInvalid) {
        this.isInvalid = isInvalid;
    }

    public int getIsPrivacy() {
        return isPrivacy;
    }

    public void setIsPrivacy(int isPrivacy) {
        this.isPrivacy = isPrivacy;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public int getIsReview() {
        return isReview;
    }

    public void setIsReview(int isReview) {
        this.isReview = isReview;
    }

    public int getIsTouchID() {
        return isTouchID;
    }

    public void setIsTouchID(int isTouchID) {
        this.isTouchID = isTouchID;
    }

    public String getLastDiaryDate() {
        return lastDiaryDate;
    }

    public void setLastDiaryDate(String lastDiaryDate) {
        this.lastDiaryDate = lastDiaryDate;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPrivacyPwd() {
        return privacyPwd;
    }

    public void setPrivacyPwd(String privacyPwd) {
        this.privacyPwd = privacyPwd;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getStarCount() {
        return starCount;
    }

    public void setStarCount(int starCount) {
        this.starCount = starCount;
    }

    public String getVipEndDate() {
        return vipEndDate;
    }

    public void setVipEndDate(String vipEndDate) {
        this.vipEndDate = vipEndDate;
    }

    public int getVipType() {
        return vipType;
    }

    public void setVipType(int vipType) {
        this.vipType = vipType;
    }

    public int getWriteUpCount() {
        return writeUpCount;
    }

    public void setWriteUpCount(int writeUpCount) {
        this.writeUpCount = writeUpCount;
    }
}
