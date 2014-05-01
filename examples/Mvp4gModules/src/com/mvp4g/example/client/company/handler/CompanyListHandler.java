package com.mvp4g.example.client.company.handler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.mvp4g.client.annotation.EventHandler;
import com.mvp4g.client.event.BaseEventHandler;
import com.mvp4g.example.client.company.CompanyEventBus;
import com.mvp4g.example.client.company.CompanyServiceAsync;
import com.mvp4g.example.client.company.bean.CompanyBean;

import java.util.List;

//this is an example of how viewless handler can be used to proxy calls to a service
@EventHandler
public class CompanyListHandler
  extends BaseEventHandler<CompanyEventBus> {

  @Inject
  private CompanyServiceAsync service = null;

  private int               start     = 0;
  private List<CompanyBean> companies = null;

  public void onGetCompanyList(final int start,
                               int end) {
    int companyCount = (companies == null) ? 0 : companies.size();
    if ((start >= this.start) && (end < (this.start + companyCount))) {
      eventBus.companyListRetrieved(getSubList(start,
                                               end));
    } else {
      service.getCompanies(start,
                           end,
                           new AsyncCallback<List<CompanyBean>>() {

                             public void onSuccess(List<CompanyBean> result) {
                               companies = result;
                               CompanyListHandler.this.start = start;
                               eventBus.companyListRetrieved(result);
                             }

                             public void onFailure(Throwable caught) {

                             }
                           });
    }

  }

  public List<CompanyBean> getSubList(int start,
                                      int end) {
    int startIndex = start - this.start;
    int endIndex = end - this.start + 1;
    return companies.subList(startIndex,
                             endIndex);
  }

}
