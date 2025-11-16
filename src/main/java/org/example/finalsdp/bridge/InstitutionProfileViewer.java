package org.example.finalsdp.bridge;

import org.example.finalsdp.abstractfactory.InstStaff;
import org.example.finalsdp.abstractfactory.InstStudent;

public class InstitutionProfileViewer extends ProfileViewer {
    private Object member;
    private String memberType;

    public InstitutionProfileViewer(DisplayFormat displayFormat, Object instMember, String type) {
        super(displayFormat);
        this.member = instMember;
        this.memberType = type;
    }

    @Override
    public void renderProfile() {
        format.showHeader(memberType + " Information");

        String details = "";
        if (member instanceof InstStudent) {
            details = ((InstStudent) member).getStudentDetails();
        } else if (member instanceof InstStaff) {
            details = ((InstStaff) member).getStaffDetails();
        }

        format.showContent(details);
        format.showFooter();
    }
}
