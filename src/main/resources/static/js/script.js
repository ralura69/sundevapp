$(function(){
	$('input[name=birth]').change(function(){
		let input = $('input[name=birth]').val();
		if (!input.match(/\d{4}-\d{2}-\d{2}/g))
			return;
		let age = getAge(input.substr(0, 4), input.substr(5, 2), input.substr(8, 2));
		$('#age').val(age);
	});

	$('#passwordFlg').change(function(){
		switchPasswordArea();
	});
	switchPasswordArea();

	$('.deleteBtn').on('click', function(){
		$('#myModal').modal('show')
		return false;
	});
	$('.searchDeleteBtn').on('click', function(){
		$('#searchDeleteUserId').val($(this).attr('id'));
		$('#myModal').modal('show')
		return false;
	});

	$('.deleteSubmit').on('click', function(){
		$('#formDelete').submit();
		return false;
	});
});

function getAge(year, month, day){
	let birthday  = new Date(year, month - 1, day);
	let today = new Date();
	let thisYearBirthday = new Date(today.getFullYear(), birthday.getMonth(), birthday.getDate());
	let age = today.getFullYear() - birthday.getFullYear();
	let val = (today < thisYearBirthday) ? age - 1 : age;
	return val > -1 ? val : '';
}
function switchPasswordArea(flg) {
	if ($('#passwordFlg').prop('checked'))
		$('.password-area').addClass('show');
	else
		$('.password-area').removeClass('show');

}