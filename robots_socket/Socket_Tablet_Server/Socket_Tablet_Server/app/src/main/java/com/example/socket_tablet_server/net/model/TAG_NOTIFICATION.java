package com.example.socket_tablet_server.net.model;

public class TAG_NOTIFICATION {
	/**
	 * The move task is started.	"모바일 작업 시작"
	 */
	public static final String CD_MOVE_TASK_STARTED         = "01001";
	
	/**
	 * The move task is finished.	모바일 작업 완료됨.
	 */
	public static final String CD_MOVE_TASK_FINISHED        = "01002";
	
	/**
	 * The move task is failed.	모바일 작업 실패
	 */
	public static final String CD_MOVE_TASK_FAILED          = "01003";
	
	/**
	 * The move task is canceled.	이동 작업 취소.
	 */
	public static final String CD_MOVE_TASK_CANCELED        = "01004";
	
	/**
	 * The move task is retried.	모바일 재시도
	 */
	public static final String CD_MOVE_TASK_RETRIED         = "01005";
	
	/**
	 * The robot may be trapped.	로봇이 갇혀있음
	 */
	public static final String CD_ROBOT_MAY_TRAPPED         = "01006";
	
	/**
	 * Start to leave charging pile.	충전 파일 출발
	 */
	public static final String CD_START_LEAVE_CHARGING_PILE     = "01010";
	
	/**
	 * Succeed to leave charging pile.	충전 파일출발 성공
	 */
	public static final String CD_SUCCEED_LEAVE_CHARGING_PILE   = "01011";
	
	/**
	 * Failed to leave charging plie.	충전 파일 출발 실패
	 */
	public static final String CD_FAILED_LEAVE_CHARGING_PILE    = "01012";
	
	/**
	 * Start to auto dock to charging pile.	충전파일도킹 시작
	 */
	public static final String CD_START_AUTO_DOCK_CHARGING_PILE = "01020";
	
	/**
	 * Succced to auto dock to charging pile.	충전 파일 도킹 성공
	 */
	public static final String CD_SUCCED_AUTO_DOCK_CHARGING_PILE = "01021";
	
	/**
	 * Failed to auto dock to charging pile.	충전파일도킹 실패
	 */
	public static final String CD_FAILED_AUTO_DOCK_CHARGING_PILE    = "01022";
	
	/**
	 * Failed to to receive valid data.	후방 실패 이유1
	 */
	public static final String CD_FAILED_RECEIVE_VALID_DATA         = "01023";
	
	/**
	 * Failed to find any feature around the robot.	후방 실패 이유2
	 */
	public static final String CD_FAILED_FIND_FEATURE_AROUND        = "01024";
	
	/**
	 * Failed to catch power status.	후방 실패 이유3
	 */
	public static final String CD_FAILED_CATCH_POWER_STATUS         = "01025";
	
	/**
	 * Failed to catch infrared signal.	후방 실패 이유4。엘리베이터 작업
	 */
	public static final String CD_FAILED_CATCH_INFRARED_SIGNAL      = "01026";
	
	/**
	 * Start to go to lift.	E/V 문으로 출발
	 */
	public static final String CD_START_GO_TO_LIFT                  = "04000";
	
	/**
	 * Succeed to go to lift.	E/V 도착 성공
	 */
	public static final String CD_SUCCEED_GO_TO_LIFT                = "04001";
	
	/**
	 * Failed to go to lift.	E/V 도착 실패
	 */
	public static final String CD_FAILED_GO_TO_LIFT                 = "04002";
	
	/**
	 * Start to call lift.	E/V 호출 시작
	 */
	public static final String CD_START_CALL_LIFT                   = "04010";
	
	/**
	 * Succeed to call lift.	E/V 호출 성공
	 */
	public static final String CD_SUCCEED_CALL_LIFT                 = "04011";
	
	/**
	 * Call lift more than 3 minutes.	E/V 3분이상 호출 하였으나 미 도착
	 */
	public static final String CD_CALL_LIFT_MORE_THAN_3M            = "04013";
	
	/**
	 * Start to take lift.	E/V 탑승 시작
	 */
	public static final String CD_START_TAKE_LIFT                   = "04020";
	
	/**
	 * Succeed to take lift.	E/V 탑승 성공
	 */
	public static final String CD_SUCCEED_TAKE_LIFT                 = "04021";
	
	/**
	 * Take lift more than 3 minutes.	E/V 탑승 시 3분이상 소요
	 */
	public static final String CD_TAKE_LIFT_MORE_THAN_3M            = "04023";
	
	/**
	 * Start to enter lift.	E/V 내부 출발
	 */
	public static final String CD_START_ENTER_LIFT                  = "04030";
	
	/**
	 * Succeed to enter lift.	E/V 내부도착 성공
	 */
	public static final String CD_SUCCEED_ENTER_LIFT                = "04031";
	
	/**
	 * Failed to enter lift.	E/V 내부도착 실패
	 */
	public static final String CD_FAILED_ENTER_LIFT                = "04032";
	
	/**
	 * Start to avoid liftt.	E/V 탑승 실패, 회피시작
	 */
	public static final String CD_START_AVOID_LIFT                  = "04040";
	
	/**
	 * Finish to avoid lift.	E/V 피하기 종료
	 */
	public static final String CD_FINISH_AVOID_LIFT                 = "04041";
	
	/**
	 * Start to exit lift.	E/V 나가기 시작
	 */
	public static final String CD_START_EXIT_LIFT                   = "04050";
	
	/**
	 * Succeed to exit lift.	E/V 나가기 성공
	 */
	public static final String CD_SUCCEED_EXIT_LIFT                 = "04051";
	
	/**
	 * Failed to exit lift.	E/V 나오기 실패
	 */
	public static final String CD_FAILED_EXIT_LIFT                  = "04052";
	
	/**
	 * Start to back to lift.	E/V 돌아가기 시작
	 */
	public static final String CD_START_BACK_LIFT                   = "04060";
	
	/**
	 * Succeed to back to lift.	E/V 돌아가기 성공
	 */
	public static final String CD_SUCCEED_BACK_LIFT                 = "04061";
	
	/**
	 * Failed to back to lift.	E/V 돌아가기 실패。국가 관련
	 */
	public static final String CD_FAILED_BACK_LIFT                  = "04062";
	
	/**
	 * Poweroff notice.	전원 끄기
	 */
	public static final String CD_POWER_OFF_NOTICE                  = "02000";
	
	/**
	 * Charge status on.	충전되지 않은 상태 => 충전 된 상태
	 */
	public static final String CD_CHARGE_STATUS_ON                  = "02001";
	
	/**
	 * Charge status off.	충전 상태 => 충전되지 않음
	 */
	public static final String CD_CHARGE_STATUS_OFF                 = "02002";
	
	/**
	 * Emergency stop on.	비상 정지 상태 => 비상 정지 상태
	 */
	public static final String CD_EMERGENCY_STOP_ON                 = "02003";
	
	/**
	 * Emergency stop off.	비상 정지 상태 => 비상 정지
	 */
	public static final String CD_EMERGENCY_STOP_OFF                = "02004";

	/**
	 * Failed to make global plan
	 */
	public static final String CD_FAIL_GLOBAL_PLAN					= "01203";
}
