package com.sponsors.repository;

import com.sponsors.dto.CaseReportDto;
import com.sponsors.model.CaseStatusChangeLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CaseStatusChangeLogRepository extends CrudRepository<CaseStatusChangeLog, Long> {

    @Query(value = " SELECT DISTINCT x.candidate_id,x.diff, case_status.status " +
            " FROM (" +
            " SELECT caseChangeLog.candidate_id,  max(datediff (caseChangeLog.end_time , caseChangeLog.start_time) ) as diff" +
            " FROM candidate_status_change_log caseChangeLog " +
            " WHERE caseChangeLog.candidate_id IN (:candidateIds)" +
            " AND caseChangeLog.end_time IS NOT NULL " +
            " GROUP BY caseChangeLog.candidate_id" +
            " ) x INNER JOIN candidate_status_change_log newChangeLog on x.candidate_id = newChangeLog.candidate_id " +
            " INNER JOIN case_status on newChangeLog.case_status_id = case_status.id" +
            " AND x.diff = datediff (newChangeLog.end_time , newChangeLog.start_time)  ",
            nativeQuery = true)
    List<Object[]> findByLongestInterviewSchedule(@Param("candidateIds") Set<Long> candidateIds);
}