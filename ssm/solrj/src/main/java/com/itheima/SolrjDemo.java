package com.itheima;


/*
    solrj入门程序
 */

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class SolrjDemo {

    /*
        添加（更新）索引
     */

    @Test
    public void addOrUpdateIndex() throws Exception {
        //1.建立HttpSolrServier服务对象，连接solr服务
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://127.0.0.1:8082/solr/");
        //2.建立文档对象（SolrInputDocument）
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("id","9527");
       // doc.addField("name","solr is a good things");
        //更新
        doc.addField("name","solr and lucene are  good things");

        //3.执行添加
        httpSolrServer.add(doc);

        //4.提交
        httpSolrServer.commit();
    }

    /*
        根据id删除索引
     */
    @Test
    public void deleteIndexById() throws IOException, SolrServerException {
        //1.建立HttpSolrServer对象，连接solr服务
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://127.0.0.1:8082/solr/");
        //2.执行删除
        httpSolrServer.deleteById("9527");
        //3.提交
        httpSolrServer.commit();

    }

    /*
       根据条件删除索引
     */
    @Test
    public void deleteIndexByQuery() throws IOException, SolrServerException {
        //1.建立HttpSolrServer对象，连接solr服务
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://127.0.0.1:8082/solr/");
        //2.执行删除
        httpSolrServer.deleteByQuery("name:solr");
        //3.提交
        httpSolrServer.commit();

    }


    /*
        查询索引
     */

    @Test
    public void queryIndex() throws SolrServerException {
        //1.建立HttpSolrServer对象，连接solr服务
        HttpSolrServer httpSolrServer = new HttpSolrServer("http://127.0.0.1:8082/solr/");
        //2.建立查询对象（SolrQuery）
        SolrQuery sq = new SolrQuery("*:*");

        //3.使用HttpSolrServer对象，执行查询，返回查询结果集（QueryResponse）
        QueryResponse query = httpSolrServer.query(sq);
        //4.从QueryResponse对象中，获取查询的结果集（SolrDocumentList）
        SolrDocumentList results = query.getResults();

        //5.处理结果集
        //5.1 实际查询的结果数量
        System.out.println("实际搜索到的结果数量：" + results.getNumFound());

        //5.2打印结果集
        for(SolrDocument doc : results){
            System.out.println("------------------分割线----------------------");
            System.out.println("id域：" + doc.get("id"));
            System.out.println("name域：" + doc.get("name"));
        }

    }









}
